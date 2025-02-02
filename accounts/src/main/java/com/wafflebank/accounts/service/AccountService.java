package com.wafflebank.accounts.service;

import com.wafflebank.accounts.model.account.AccountData;
import com.wafflebank.accounts.model.customer.CustomerData;

public interface AccountService {
    void createAccount(CustomerData customer);
    AccountData getAccount(Long accountNumber);
    AccountData getAccountByMobileNumber(String mobileNumber);
    boolean updateAccount(AccountData account);
    boolean deleteAccount(Long accountNumber);
    boolean deleteAccountByCustomerId(Long customerId);
}
