package com.wafflebank.loans.service;

import com.wafflebank.loans.entity.LoanEntity;
import com.wafflebank.loans.exception.FileExistException;
import com.wafflebank.loans.exception.ResourceNotFoundException;
import com.wafflebank.loans.mapper.LoanDataMapper;
import com.wafflebank.loans.model.loan.LoanData;
import com.wafflebank.loans.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    @Override
    public void createLoan(LoanData loanData) {
        String mobileNumber = loanData.getMobileNumber();
        if (loanRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new FileExistException(
                    String.format("Loan already exists (mobile number: %s)", mobileNumber)
            );
        } else {
            LoanEntity loanToSave = LoanDataMapper.toEntity(loanData);
            loanRepository.save(loanToSave);
        }
    }

    @Override
    public List<LoanData> findLoan(String loanNumber) {
        List<LoanEntity> loanEntities = loanRepository.findByLoanNumber(loanNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        if (loanEntities.isEmpty()) throw new ResourceNotFoundException("Loan not found");

        return loanEntities.stream()
                .map(LoanDataMapper::toData)
                .collect(Collectors.toList());
    }

    @Override
    public LoanData findByMobileNumber(String mobileNumber) {
        LoanEntity loanEntity = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        return LoanDataMapper.toData(loanEntity);
    }

    @Override
    public boolean updateLoan(LoanData loanData) {
        loanRepository.findById(loanData.getLoanId())
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        loanRepository.save(LoanDataMapper.toEntity(loanData));
        return true;
    }

    @Override
    public boolean deleteLoan(LoanData loanData) {
        loanRepository.findById(loanData.getLoanId())
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        loanRepository.deleteById(loanData.getLoanId());
        return true;
    }

    @Override
    public boolean deleteByMobileNumber(String mobileNumber) {
        loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        loanRepository.deleteByMobileNumber(mobileNumber);
        return true;
    }
}
