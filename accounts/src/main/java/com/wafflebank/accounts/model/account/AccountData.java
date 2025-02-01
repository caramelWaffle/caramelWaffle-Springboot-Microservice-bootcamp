package com.wafflebank.accounts.model.account;

import lombok.Data;

@Data
public class AccountData {
    private long accountNumber;
    private AccountType accountType;
    private String branch;
}