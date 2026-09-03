package com.example.accounts_microservices.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExisitException extends  RuntimeException{

    public CustomerAlreadyExisitException(String message) {
        super(message);
    }
}
