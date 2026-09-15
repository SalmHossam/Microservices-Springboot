package com.example.loans_microservices.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class LoansDTO {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{11})",message = "Mobile Number must be 11 digits")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")

    private String loanNumber;

    @NotEmpty(message = "LoanType can not be a null or empty")

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

}