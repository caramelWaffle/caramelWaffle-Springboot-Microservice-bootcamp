package com.wafflebank.accounts.model.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Customer information used for account operations")
public class CustomerData {

    @Schema(description = "Unique identifier for the customer", example = "1001")
    @NotEmpty
    @Size(min = 5, max = 30)
    private long customerId;

    @Schema(description = "Full name of the customer", example = "John Doe")
    private String name;

    @Schema(description = "Email address of the customer", example = "john.doe@example.com")
    @NotEmpty
    @Email
    private String email;

    @Schema(description = "Mobile phone number of the customer", example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobileNumber;
}
