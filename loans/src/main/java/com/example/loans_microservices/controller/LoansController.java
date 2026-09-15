package com.example.loans_microservices.controller;

import com.example.loans_microservices.DTOs.LoansDTO;
import com.example.loans_microservices.DTOs.ResponseDTO;
import com.example.loans_microservices.constant.LoansConstants;
import com.example.loans_microservices.service.LoanService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LoansController {

    private final LoanService loanService;

    @PostMapping("/create-loan")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{11})",message = "Mobile number must be 11 digits")
                                                      String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return  ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }

    @GetMapping("/loans/{mobileNumber}")
    public ResponseEntity<LoansDTO> fetchLoan(@RequestParam @Pattern(regexp="(^$|[0-9]{11})",message = "Mobile number must be 11 digits") String mobileNumber ) {
       LoansDTO loansDTO=loanService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(
               loansDTO);
    }

    @PutMapping("/update-loan")
    public ResponseEntity<ResponseDTO> updateLoan(@RequestBody LoansDTO loansDTO) {
        boolean isLoanUpdated = loanService.updateLoan(loansDTO);
        if (isLoanUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDTO(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("/delete-loan")
    public ResponseEntity<ResponseDTO>deleteLoan(@RequestParam @Pattern(regexp="(^$|[0-9]{11})",message = "Mobile number must be 11 digits") String mobileNumber) {
        boolean isLoanDeleted = loanService.deleteLoan(mobileNumber);

        if(isLoanDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDTO(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));
        }
    }



}
