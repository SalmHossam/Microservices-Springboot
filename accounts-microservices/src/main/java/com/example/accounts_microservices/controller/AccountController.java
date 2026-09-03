package com.example.accounts_microservices.controller;

import com.example.accounts_microservices.DTOs.AccountDTO;
import com.example.accounts_microservices.DTOs.CustomerDTO;
import com.example.accounts_microservices.DTOs.ResponseDTO;
import com.example.accounts_microservices.constant.AccountsConstants;
import com.example.accounts_microservices.exceptions.GlobalException;
import com.example.accounts_microservices.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api",produces = APPLICATION_JSON_VALUE)
public class AccountController {
    private final IAccountsService accountsService;

    @PostMapping(path = "/create-account")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountsService.createAccount(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseDTO(AccountsConstants.STATUS_CODE_201,
                            AccountsConstants.CREATE_ACCOUNT_SUCCESS)
            );
    }

    @GetMapping(path="/get-account")
    public ResponseEntity<CustomerDTO> getAccount(@RequestParam String mobileNumber){
        CustomerDTO customerDTO = accountsService.getCustomer(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @PutMapping(path="/update-account")
    public ResponseEntity<ResponseDTO> updateAccount(@RequestBody CustomerDTO customerDTO){

        boolean isUpdate=accountsService.updateAccount(customerDTO);
        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(AccountsConstants.STATUS_CODE_201,
                            AccountsConstants.UPDATE_ACCOUNT_SUCCESS)
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));

        }
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam String mobileNumber){
        boolean isDelete=accountsService.deleteAccount(mobileNumber);
        if(isDelete){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(AccountsConstants.STATUS_CODE_200,
                            AccountsConstants.DELETE_ACCOUNT_SUCCESS)
            );

        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417,AccountsConstants.DELETE_ACCOUNT_ERROR));

        }

    }
}
