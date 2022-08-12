package com.samueljavaspringboot.bankng_system.service;

import com.samueljavaspringboot.bankng_system.entity.Account;
import com.samueljavaspringboot.bankng_system.entity.Transaction;
import com.samueljavaspringboot.bankng_system.exception.exceptionHandler.InsufficientFundException;
import com.samueljavaspringboot.bankng_system.dto.AccountDTO;
import com.samueljavaspringboot.bankng_system.dto.DepositForm;
import com.samueljavaspringboot.bankng_system.dto.WithdrawalForm;
import com.samueljavaspringboot.bankng_system.repos.AccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.samueljavaspringboot.bankng_system.repos.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private final ModelMapper mapper;

    public AccountServiceImp(final AccountRepository accountRepository, TransactionRepository transactionRepository, ModelMapper mapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public void withdraw(WithdrawalForm withdrawalForm) {
        Transaction transaction = new Transaction();
        String accountNumber = withdrawalForm.getAccountNumber();
        BigDecimal amount = withdrawalForm.getAmount();
        String currency = withdrawalForm.getCurrency();
        Optional<Account> sourceAccount = Optional.ofNullable(accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        sourceAccount.get().getAccountType().equalsIgnoreCase(currency);
        if (sourceAccount.get().getBalance().compareTo(amount) < 0) throw new InsufficientFundException("InsufficientFund");
        sourceAccount.get().setBalance(sourceAccount.get().getBalance().subtract(amount));
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setAccount(sourceAccount.get());
        transactionRepository.save(transaction);
        accountRepository.save(sourceAccount.get());


    }

    @Transactional
    @Override
    public void deposit(DepositForm depositForm) {
        String accountNumber = depositForm.getAccountNumber();
        BigDecimal amount = depositForm.getAmount();
        String currency = depositForm.getCurrency();
        Optional<Account> account = Optional.ofNullable(accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        account.get().getAccountType().equalsIgnoreCase(currency);
        account.get().setBalance(account.get().getBalance().add(amount));
        accountRepository.save(account.get());


    }

    public List<AccountDTO> findAll() {
        return accountRepository.findAll(Sort.by("id"))
                .stream()
                .map(account -> mapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }

    public AccountDTO get(final Long id) {
        return accountRepository.findById(id)
                .map(account -> mapper.map(account, AccountDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final AccountDTO accountDTO) {
        final Account account = new Account();
        mapper.map(accountDTO, account);
        return accountRepository.save(account).getId();
    }

    public void update(final Long id, final AccountDTO accountDTO) {
        final Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.map(accountDTO, account);
        accountRepository.save(account);
    }

    public void delete(final Long id) {
        accountRepository.deleteById(id);
    }



}
