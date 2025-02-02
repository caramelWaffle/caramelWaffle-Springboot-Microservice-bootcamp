package com.wafflebank.accounts.model.account;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountData {
    private long accountNumber;
    private AccountType accountType;
    private String branch;
    private String mobileNumber;
}