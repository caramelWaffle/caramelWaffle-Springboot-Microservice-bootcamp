package com.wafflebank.accounts.controller;

import com.wafflebank.accounts.model.customer.CustomerData;
import com.wafflebank.accounts.model.network.ResponseData;
import com.wafflebank.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createAccount(@RequestBody CustomerData customerData) {
        accountService.createAccount(customerData);

        HttpStatus status = HttpStatus.CREATED;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(status)
                .body(body);
    }
}
