package com.wafflebank.accounts.service;

import com.wafflebank.accounts.model.customer.CustomerData;

public interface AccountService {
    void createAccount(CustomerData customer);
}
