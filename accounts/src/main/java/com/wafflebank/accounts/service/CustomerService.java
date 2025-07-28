package com.wafflebank.accounts.service;

import com.wafflebank.accounts.model.account.AccountData;
import com.wafflebank.accounts.model.customer.CustomerData;
import com.wafflebank.accounts.model.customer.CustomerFullDetail;

public interface CustomerService {
    CustomerFullDetail fetchCustomerByMobileNumber(String mobileNumber);
}
