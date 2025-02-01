package com.wafflebank.accounts.service;

import com.wafflebank.accounts.entity.AccountEntity;
import com.wafflebank.accounts.entity.CustomerEntity;
import com.wafflebank.accounts.exception.CustomerExistException;
import com.wafflebank.accounts.mapper.CustomerDataMapper;
import com.wafflebank.accounts.model.account.AccountType;
import com.wafflebank.accounts.model.customer.CustomerData;
import com.wafflebank.accounts.repository.AccountRepository;
import com.wafflebank.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerData customer) {
        if (customerRepository.findByMobileNumber(customer.getMobileNumber()).isPresent()) {
            throw new CustomerExistException(
                    String.format("Customer already exists (mobile number: %s)", customer.getMobileNumber())
            );
        } else {
            CustomerEntity customerToSave = CustomerDataMapper.toEntity(customer);
            customerToSave.setCreatedAt(LocalDateTime.now());
            customerToSave.setCreatedBy("waffle");
            CustomerEntity customerEntity = customerRepository.save(customerToSave);

            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setCustomerId(customerEntity.getCustomerId());
            accountEntity.setAccountNumber(generateRandomAccountNumber());
            accountEntity.setMobileNumber(customerEntity.getMobileNumber());
            accountEntity.setAccountType(AccountType.SAVING.name());
            accountEntity.setCreatedAt(LocalDateTime.now());
            accountEntity.setCreatedBy("waffle");
            accountRepository.save(accountEntity);
        }
    }

    public static long generateRandomAccountNumber() {
        Random random = new Random();
        return 1000000000L + random.nextInt(900000000);  // Generates a number between 1000000000 and 1999999999
    }
}
