package com.wafflebank.loans.model.loan;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Valid
@Schema(description = "Loan information used for account operations")
public class LoanData {

    @Schema(description = "Unique identifier for the loan", example = "1001")
    @NotNull
    private int loanId;

    @Schema(description = "Mobile phone number of the loan", example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobileNumber;

    @Schema(description = "Number of the loan", example = "1001")
    @NotNull
    private String loanNumber;

    @Schema(description = "Type of the loan", example = "TODO")
    @NotNull
    private String loanType;

    @Schema(description = "AmountPaid of the loan", example = "TODO")
    private int amountPaid;

    @Schema(description = "outStandingAmount of the loan", example = "TODO")
    private int outStandingAmount;
}
