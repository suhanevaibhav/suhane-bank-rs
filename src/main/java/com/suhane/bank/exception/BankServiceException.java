package com.suhane.bank.exception;

public class BankServiceException extends RuntimeException{

    private String message;

    public BankServiceException(String message) {
        super(message);
        this.message = message;
    }
    public BankServiceException() {

    }
}
