package com.example.accounts_microservices.DTOs;

import lombok.Data;

@Data
public class AccountDTO {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
