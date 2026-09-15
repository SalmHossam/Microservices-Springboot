package com.example.loans_microservices.service;

import com.example.loans_microservices.DTOs.LoansDTO;
import com.example.loans_microservices.constant.LoansConstants;
import com.example.loans_microservices.entity.Loans;
import com.example.loans_microservices.exceptions.LoanAlreadyExisitException;
import com.example.loans_microservices.exceptions.ResourceNotFoundException;
import com.example.loans_microservices.mapper.LoanMapper;
import com.example.loans_microservices.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public void createLoan(String mobileNumber) {
        Optional<Loans> existingLoan = loanRepository.findByMobileNumber(mobileNumber);
        if (existingLoan.isPresent()) {
            throw new LoanAlreadyExisitException("Loan already exists with given mobile number"+ mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }


    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    public LoansDTO fetchLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.mapToLoanDTo(loans, new LoansDTO());
    }

    public boolean updateLoan(LoansDTO loansDTO){
        Loans loans = loanRepository.findByMobileNumber(loansDTO.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", loansDTO.getMobileNumber())
        );
        LoanMapper.mapToLoan(loans, loansDTO);
        loanRepository.save(loans);
        return true;
    }

    public  boolean deleteLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loanRepository.delete(loans);
        return true;
    }

}
