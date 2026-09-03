package com.example.cards_microservices.exceptions;

public class CardAlreadyExisitException extends RuntimeException {
    public CardAlreadyExisitException(String message) {
        super(message);
    }
}
