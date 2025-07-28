package com.wafflebank.accounts.model.customer;

import com.wafflebank.accounts.model.account.AccountData;
import com.wafflebank.accounts.model.feign.card.CardData;
import com.wafflebank.accounts.model.feign.loan.LoanData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
    description = "Customer full detail of Customer, Account and card information used for operations",
    title = "CustomerFullDetail",
    name = "CustomerFullDetail"
)
public class CustomerFullDetail {
    private CustomerData customerData;
    private AccountData accountData;
    private LoanData loanData;
    private CardData cardData;
}
