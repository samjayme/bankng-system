package com.samueljavaspringboot.bankng_system.service;

import com.samueljavaspringboot.bankng_system.dto.AccountDTO;
import com.samueljavaspringboot.bankng_system.dto.DepositForm;
import com.samueljavaspringboot.bankng_system.dto.WithdrawalForm;

import java.util.List;

public interface AccountService {
    void withdraw(WithdrawalForm withdrawalForm);
    void deposit(DepositForm depositForm);
    List<AccountDTO> findAll();
    AccountDTO get(final Long id);
    Long create(final AccountDTO accountDTO);
    void update(final Long id, final AccountDTO accountDTO);
    void delete(final Long id);
}
