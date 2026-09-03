package com.example.accounts_microservices.service;

import com.example.accounts_microservices.DTOs.CustomerDTO;

public interface IAccountsService {

    void createAccount(CustomerDTO customerDTO);
    CustomerDTO getCustomer(String mobileNumber);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);

}
