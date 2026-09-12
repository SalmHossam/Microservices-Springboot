package com.example.accounts_microservices.service;

import com.example.accounts_microservices.DTOs.AccountDTO;
import com.example.accounts_microservices.DTOs.CustomerDTO;
import com.example.accounts_microservices.constant.AccountsConstants;
import com.example.accounts_microservices.exceptions.CustomerAlreadyExisitException;
import com.example.accounts_microservices.exceptions.ResourceNotFoundException;
import com.example.accounts_microservices.mapper.AccountMapper;
import com.example.accounts_microservices.mapper.CustomerMapper;
import com.example.accounts_microservices.repository.AccountRepo;
import com.example.accounts_microservices.repository.CustomerRepo;
import com.example.accounts_microservices.entity.Accounts;
import com.example.accounts_microservices.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class AccountsService implements  IAccountsService {

    private final CustomerRepo customerRepo;
    private final AccountRepo accountRepo;
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer>findCustomer=customerRepo.findByMobileNumber(customerDTO.getMobileNumber());
        if(findCustomer.isPresent()){
            throw new CustomerAlreadyExisitException("Customer already registered with this phone number");
        }

        Customer savedCustomer=customerRepo.save(customer);
        accountRepo.save(createNewAccount(savedCustomer));
    }

  public CustomerDTO getCustomer(String mobileNumber){
       Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
       );
       Accounts account = accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
               ()->new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
       );

       CustomerDTO customerDTO=CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
       customerDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account,new AccountDTO()));
        return customerDTO;
  }

    public boolean updateAccount(CustomerDTO customerDTO){
        boolean isUpdated=false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        if(accountDTO!=null){
            Accounts account = accountRepo
                    .findByAccountNumber(accountDTO.getAccountNumber())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Account",
                                    "accountNumber",
                                    accountDTO.getAccountNumber().toString()
                            )
                    );

            AccountMapper.mapToAccount(accountDTO, account);

            accountRepo.save(account);

            Long customerId=account.getCustomerId();
            Customer customer=customerRepo.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("customer","customerId",customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepo.save(customer);
            isUpdated=true;
        }



        return isUpdated;
    }

    public boolean deleteAccount(String mobileNumber){
        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteByCustomerId(customer.getCustomerId());
        return true;
    }


    private Accounts createNewAccount(Customer customer) {
       Accounts account = new Accounts();
       account.setCustomerId(customer.getCustomerId());
       long accountNumber = 1000000000000L+new Random().nextLong(90000000);
       account.setAccountNumber(accountNumber);
      account.setAccountType(AccountsConstants.ACCOUNT_TYPE);
      account.setBranchAddress(AccountsConstants.ADDRESS);

      return account;
    }
}
