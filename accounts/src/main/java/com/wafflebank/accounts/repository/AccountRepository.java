package com.wafflebank.accounts.repository;

import com.wafflebank.accounts.entity.AccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByMobileNumber(String mobileNumber);

    Optional<AccountEntity> findByCustomerId(long customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(long customerId);
}
