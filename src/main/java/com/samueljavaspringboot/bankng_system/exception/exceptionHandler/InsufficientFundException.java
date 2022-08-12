package com.samueljavaspringboot.bankng_system.exception.exceptionHandler;

public class InsufficientFundException extends RuntimeException{
    public InsufficientFundException(String message) {
        super(message);
    }
}
