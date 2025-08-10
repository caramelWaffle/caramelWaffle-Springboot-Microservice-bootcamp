package com.wafflebank.accounts.controller;

import com.wafflebank.accounts.model.customer.CustomerFullDetail;
import com.wafflebank.accounts.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RefreshScope
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    private Logger logger = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/fullDetail")
    public ResponseEntity<CustomerFullDetail> fetchCustomerFullDetail(
            @RequestHeader("X-Correlation-ID") String correlationId,
            @RequestParam String mobileNumber) {
        logger.info("/customer/fullDetail - Correlation ID: " + correlationId);
        CustomerFullDetail customerFullDetail = customerService.fetchCustomerByMobileNumber(mobileNumber);
        HttpStatus status = customerFullDetail == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(customerFullDetail);
    }
}
