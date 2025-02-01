package com.wafflebank.accounts.model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerData {
    private long customerId;
    private String name;
    private String email;
    private String mobileNumber;
}