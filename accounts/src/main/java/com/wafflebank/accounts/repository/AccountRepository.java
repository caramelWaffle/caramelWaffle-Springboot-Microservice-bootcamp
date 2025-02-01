package com.wafflebank.accounts.repository;

import com.wafflebank.accounts.entity.Account;
import com.wafflebank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
