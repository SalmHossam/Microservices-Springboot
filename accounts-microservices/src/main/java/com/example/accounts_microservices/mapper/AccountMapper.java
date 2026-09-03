package com.example.accounts_microservices.mapper;

import com.example.accounts_microservices.DTOs.AccountDTO;
import com.example.accounts_microservices.entity.Accounts;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public static Accounts mapToAccount(AccountDTO dto, Accounts account) {
        account.setAccountNumber(dto.getAccountNumber());
        account.setAccountType(dto.getAccountType());
        account.setBranchAddress(dto.getBranchAddress());
        return account;

    }

    public static AccountDTO mapToAccountDTO(Accounts account, AccountDTO dto) {
        dto.setAccountNumber(account.getAccountNumber());
        dto.setAccountType(account.getAccountType());
        dto.setBranchAddress(account.getBranchAddress());
        return dto;

    }
}
