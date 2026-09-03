package com.example.accounts_microservices.mapper;

import com.example.accounts_microservices.DTOs.CustomerDTO;
import com.example.accounts_microservices.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public static Customer mapToCustomer(CustomerDTO dto, Customer customer) {

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setMobileNumber(dto.getMobileNumber());

        return customer;
    }

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO dto) {

        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setMobileNumber(customer.getMobileNumber());
        return dto;

    }
}
