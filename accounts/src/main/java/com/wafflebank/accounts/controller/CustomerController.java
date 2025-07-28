package com.wafflebank.accounts.controller;

import com.wafflebank.accounts.model.customer.CustomerFullDetail;
import com.wafflebank.accounts.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/fullDetail")
    public ResponseEntity<CustomerFullDetail> fetchCustomerFullDetail(@RequestParam String mobileNumber) {
        CustomerFullDetail customerFullDetail = customerService.fetchCustomerByMobileNumber(mobileNumber);
        HttpStatus status = customerFullDetail == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(customerFullDetail);
    }
}
