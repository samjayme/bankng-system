package com.samueljavaspringboot.bankng_system.repos;

import com.samueljavaspringboot.bankng_system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}