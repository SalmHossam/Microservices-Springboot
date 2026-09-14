package com.example.loans_microservices.exceptions;

public class LoanAlreadyExisitException extends RuntimeException {
    public LoanAlreadyExisitException(String message) {
        super(message);
    }
}
