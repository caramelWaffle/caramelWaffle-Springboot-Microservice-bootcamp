package com.wafflebank.accounts.controller;

import com.wafflebank.accounts.model.account.AccountData;
import com.wafflebank.accounts.model.customer.CustomerData;
import com.wafflebank.accounts.model.network.ResponseData;
import com.wafflebank.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private AccountService accountService;

    @GetMapping("/find")
    public ResponseEntity<AccountData> find(@RequestParam long accountNumber) {
        AccountData accountData = accountService.getAccount(accountNumber);
        HttpStatus status = accountData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return ResponseEntity
                .status(status)
                .body(accountData);
    }

    @GetMapping("/findByMobileNumber")
    public ResponseEntity<AccountData> find(@RequestParam String mobileNumber) {
        AccountData accountData = accountService.getAccountByMobileNumber(mobileNumber);
        HttpStatus status = accountData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return ResponseEntity
                .status(status)
                .body(accountData);
    }

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

    @PutMapping("/update")
    public ResponseEntity<ResponseData> updateAccount(@RequestBody AccountData accountData) {
        boolean isSuccess = accountService.updateAccount(accountData);

        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(status)
                .body(body);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData> delete(@RequestParam long accountNumber) {
        boolean isSuccess = accountService.deleteAccount(accountNumber);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(status)
                .body(body);
    }

    @DeleteMapping("/deleteByCustomerId")
    public ResponseEntity<ResponseData> deleteByCustomerId(@RequestParam long customerId) {
        boolean isSuccess = accountService.deleteAccountByCustomerId(customerId);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(status)
                .body(body);
    }
}
