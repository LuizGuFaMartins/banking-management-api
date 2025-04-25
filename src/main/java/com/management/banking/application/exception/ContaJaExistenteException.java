package com.management.banking.application.exception;

public class ContaJaExistenteException extends RuntimeException {
    public ContaJaExistenteException(String message) {
        super(message);
    }
}
