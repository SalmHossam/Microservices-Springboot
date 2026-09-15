package com.example.loans_microservices.mapper;

import com.example.loans_microservices.DTOs.LoansDTO;
import com.example.loans_microservices.entity.Loans;
import com.example.loans_microservices.DTOs.LoansDTO;

public class LoanMapper {

    public static Loans mapToLoan(Loans loans, LoansDTO loanDTo){
        loans.setAmountPaid(loanDTo.getAmountPaid());
        loans.setLoanNumber(loanDTo.getLoanNumber());
        loans.setLoanType(loanDTo.getLoanType());
        loans.setOutstandingAmount(loanDTo.getOutstandingAmount());
        loans.setTotalLoan(loanDTo.getTotalLoan());
        return loans;

    }

    public static LoansDTO mapToLoanDTo(Loans loans, LoansDTO loanDTo){
        loanDTo.setLoanNumber(loans.getLoanNumber());
        loanDTo.setLoanType(loans.getLoanType());
        loanDTo.setTotalLoan(loans.getTotalLoan());
        loanDTo.setAmountPaid(loans.getAmountPaid());
        loanDTo.setOutstandingAmount(loans.getOutstandingAmount());
        return loanDTo;

    }
}
