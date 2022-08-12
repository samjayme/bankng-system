package com.samueljavaspringboot.bankng_system.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WithdrawalForm {
    private String accountNumber;
    private BigDecimal amount;
    private String currency;
}
