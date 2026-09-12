package com.example.cards_microservices.exceptions;

import com.example.cards_microservices.DTOs.ErrorResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@AllArgsConstructor
public class GlobalException {

    private final WebRequest webRequest;

    @ExceptionHandler(CardAlreadyExistException.class)
    public ResponseEntity<?> CardAlreadyExistException(CardAlreadyExistException ex
    , WebRequest webRequest){

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(com.example.accounts_microservices.exceptions.ResourceNotFoundException.class)
    public ResponseEntity<?>handleResourceNotFoundException(com.example.accounts_microservices.exceptions.ResourceNotFoundException ex, WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }
}
