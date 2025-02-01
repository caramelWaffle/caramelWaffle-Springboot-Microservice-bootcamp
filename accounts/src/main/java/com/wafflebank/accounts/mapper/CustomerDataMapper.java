package com.wafflebank.accounts.mapper;

import com.wafflebank.accounts.entity.CustomerEntity;
import com.wafflebank.accounts.model.customer.CustomerData;

public class CustomerDataMapper {

    // Convert CustomerData to CustomerEntity
    public static CustomerEntity toEntity(CustomerData customerData) {
        if (customerData == null) {
            return null;
        }
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerData.getCustomerId());
        customerEntity.setName(customerData.getName());
        customerEntity.setEmail(customerData.getEmail());
        customerEntity.setMobileNumber(customerData.getMobileNumber());
        return customerEntity;
    }

    // Convert CustomerEntity to CustomerData
    public static CustomerData toData(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }
        return CustomerData.builder()
                .customerId(customerEntity.getCustomerId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .mobileNumber(customerEntity.getMobileNumber())
                .build();
    }
}