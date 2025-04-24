package com.wafflebank.loans.repository;

import com.wafflebank.loans.entity.LoanEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {
    Optional<LoanEntity> findByMobileNumber(String mobileNumber);
    Optional<List<LoanEntity>> findByLoanNumber(String loanNumber);

    @Transactional
    @Modifying
    void deleteByMobileNumber(String mobileNumber);
}
