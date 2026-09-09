package com.example.accounts_microservices.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    private String email;
    @Pattern(regexp = "^($|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String mobileNumber;
    private AccountDTO accountDTO;
}
