package com.example.accounts_microservices.exceptions;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends  RuntimeException {
    public  ResourceNotFoundException(String message,String ResourceName,String fieldValue) {
        super(String.format("%s not found with %s : '%s'",message,ResourceName,fieldValue));
    }
}
