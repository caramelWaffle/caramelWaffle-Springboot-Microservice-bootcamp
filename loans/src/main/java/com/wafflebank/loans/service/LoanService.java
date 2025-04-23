package com.wafflebank.loans.service;

import com.wafflebank.loans.model.loan.LoanData;

import java.util.List;

public interface LoanService {
    void createLoan(LoanData loanData);
    List<LoanData> findLoan(String loanNumber);
    LoanData findByMobileNumber(String mobileNumber);
    boolean updateLoan(LoanData loanData);
    boolean deleteLoan(LoanData loanData);
}
