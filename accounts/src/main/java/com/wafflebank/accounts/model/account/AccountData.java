package com.wafflebank.accounts.model.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Account information returned by the system")
public class AccountData {

    @Schema(description = "Unique account number", example = "1234567890")
    private long accountNumber;

    @Schema(description = "Type of account", implementation = AccountType.class)
    private AccountType accountType;

    @Schema(description = "Branch name or code associated with the account", example = "New York Downtown")
    private String branch;

    @Schema(description = "Mobile number linked to the account", example = "1234567890")
    private String mobileNumber;
}