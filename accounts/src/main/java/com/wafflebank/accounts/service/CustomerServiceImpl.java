package com.wafflebank.accounts.service;

import com.wafflebank.accounts.entity.AccountEntity;
import com.wafflebank.accounts.entity.CustomerEntity;
import com.wafflebank.accounts.exception.ResourceNotFoundException;
import com.wafflebank.accounts.mapper.AccountDataMapper;
import com.wafflebank.accounts.mapper.CustomerDataMapper;
import com.wafflebank.accounts.model.account.AccountData;
import com.wafflebank.accounts.model.customer.CustomerData;
import com.wafflebank.accounts.model.customer.CustomerFullDetail;
import com.wafflebank.accounts.model.feign.card.CardData;
import com.wafflebank.accounts.model.feign.loan.LoanData;
import com.wafflebank.accounts.repository.AccountRepository;
import com.wafflebank.accounts.repository.CustomerRepository;
import com.wafflebank.accounts.service.client.CardsFeignClient;
import com.wafflebank.accounts.service.client.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoanFeignClient loanFeignClient;

    @Override
    public CustomerFullDetail fetchCustomerByMobileNumber(String mobileNumber) {
        CustomerEntity customerEntity = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found for mobile number: " + mobileNumber)
        );

        AccountEntity accountEntity = accountRepository.findByCustomerId(customerEntity.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found for mobile number: " + mobileNumber)
        );

        AccountData accountData = AccountDataMapper.toData(accountEntity);
        CustomerData customerData = CustomerDataMapper.toData(customerEntity);
        LoanData loanData = loanFeignClient.findByMobileNumber(mobileNumber).getBody();
        CardData cardData = cardsFeignClient.findCardByMobileNumber(mobileNumber).getBody();

        if (accountData == null || customerData == null || loanData == null || cardData == null) {
            throw new ResourceNotFoundException("One or more details not found for mobile number: " + mobileNumber);
        }
        return CustomerFullDetail.builder()
                .accountData(accountData)
                .customerData(customerData)
                .loanData(loanData)
                .cardData(cardData)
                .build();
    }
}
