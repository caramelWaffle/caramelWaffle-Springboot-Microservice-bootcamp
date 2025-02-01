package com.wafflebank.accounts.model.customer;

import lombok.Data;

@Data
public class CustomerData {
    private long customerId;
    private String name;
    private String email;
    private String mobileNumber;
}