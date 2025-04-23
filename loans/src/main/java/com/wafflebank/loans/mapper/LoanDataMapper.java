package com.wafflebank.loans.mapper;

import com.wafflebank.loans.entity.LoanEntity;
import com.wafflebank.loans.model.loan.LoanData;

public class LoanDataMapper {

    // Convert LoanData to LoanEntity
    public static LoanEntity toEntity(LoanData loanData) {
        if (loanData == null) {
            return null;
        }
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setLoanId(loanData.getLoanId());
        loanEntity.setMobileNumber(loanData.getMobileNumber());
        loanEntity.setLoanNumber(loanData.getLoanNumber());
        loanEntity.setLoanType(loanData.getLoanType());
        loanEntity.setAmountPaid(loanData.getAmountPaid());
        loanEntity.setOutStandingAmount(loanData.getOutStandingAmount());
        return loanEntity;
    }

    // Convert CustomerEntity to CustomerData
    public static LoanData toData(LoanEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }
        return LoanData.builder()
                .loanId(customerEntity.getLoanId())
                .mobileNumber(customerEntity.getMobileNumber())
                .loanNumber(customerEntity.getLoanNumber())
                .loanType(customerEntity.getLoanType())
                .amountPaid(customerEntity.getAmountPaid())
                .outStandingAmount(customerEntity.getOutStandingAmount())
                .build();
    }
}