package com.samueljavaspringboot.bankng_system.repos;

import com.samueljavaspringboot.bankng_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
