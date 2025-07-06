package com.wafflebank.accounts.controller;

import com.wafflebank.accounts.model.account.AccountData;
import com.wafflebank.accounts.model.build.BuildInfo;
import com.wafflebank.accounts.model.customer.CustomerData;
import com.wafflebank.accounts.model.network.ResponseData;
import com.wafflebank.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AccountsController", description = "Controller for managing account operations")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private AccountService accountService;

    @Operation(
            summary = "Retrieve account data based on account number",
            parameters = {
                    @Parameter(
                            name = "accountNumber",
                            description = "The account number to search for",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account data found",
                            content = @Content(schema = @Schema(implementation = AccountData.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Account not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid request")
            }
    )
    @GetMapping("/find")
    public ResponseEntity<AccountData> find(@RequestParam long accountNumber) {
        AccountData accountData = accountService.getAccount(accountNumber);
        HttpStatus status = accountData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(accountData);
    }

    @Operation(
            summary = "Retrieve account data based on mobile number",
            parameters = {
                    @Parameter(
                            name = "mobileNumber",
                            description = "The mobile number to search for",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account data found",
                            content = @Content(schema = @Schema(implementation = AccountData.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Account not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid request")
            }
    )
    @GetMapping("/findByMobileNumber")
    public ResponseEntity<AccountData> find(@RequestParam String mobileNumber) {
        AccountData accountData = accountService.getAccountByMobileNumber(mobileNumber);
        HttpStatus status = accountData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(accountData);
    }

    @Operation(
            summary = "Create a new account",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customer data used for creating a new account",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CustomerData.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Account successfully created",
                            content = @Content(schema = @Schema(implementation = ResponseData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid customer data"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseData> createAccount(@RequestBody CustomerData customerData) {
        accountService.createAccount(customerData);

        HttpStatus status = HttpStatus.CREATED;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    @Operation(
            summary = "Update account details",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Account data to update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AccountData.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account successfully updated",
                            content = @Content(schema = @Schema(implementation = ResponseData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid account data"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseData> updateAccount(@RequestBody AccountData accountData) {
        boolean isSuccess = accountService.updateAccount(accountData);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    @Operation(
            summary = "Delete an account by account number",
            parameters = {
                    @Parameter(
                            name = "accountNumber",
                            description = "The account number to delete",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account successfully deleted",
                            content = @Content(schema = @Schema(implementation = ResponseData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid account number"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData> delete(@RequestParam long accountNumber) {
        boolean isSuccess = accountService.deleteAccount(accountNumber);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    @Operation(
            summary = "Delete an account by customer ID",
            parameters = {
                    @Parameter(
                            name = "customerId",
                            description = "The customer ID whose account is to be deleted",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account successfully deleted by customer ID",
                            content = @Content(schema = @Schema(implementation = ResponseData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid customer ID"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/deleteByCustomerId")
    public ResponseEntity<ResponseData> deleteByCustomerId(@RequestParam long customerId) {
        boolean isSuccess = accountService.deleteAccountByCustomerId(customerId);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    @Operation(
            description = "Get build information",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account successfully deleted by customer ID",
                            content = @Content(schema = @Schema(implementation = ResponseData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid customer ID"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/build-info")
    public ResponseEntity<BuildInfo> getBuildInformation() {
        HttpStatus status = HttpStatus.OK;

        BuildInfo body = BuildInfo.builder()
                .version(buildVersion)
                .build();

        return ResponseEntity.status(status).body(body);
    }
}
