package com.wafflebank.loans.controller;

import com.wafflebank.loans.model.loan.LoanData;
import com.wafflebank.loans.model.network.ResponseData;
import com.wafflebank.loans.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "LoansController", description = "Controller for managing loans operations")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class LoansController {

    private LoanService loanService;

    @Operation(
            summary = "Create a new Loan",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Loan data used for creating a new Loan",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoanData.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Loan successfully created",
                            content = @Content(schema = @Schema(implementation = ResponseData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid Loan data"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseData> createLoans(@Valid @RequestBody LoanData loanData) {
        loanService.createLoan(loanData);

        HttpStatus status = HttpStatus.CREATED;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    @Operation(
            summary = "Retrieve Loan data based on Loan number",
            parameters = {
                    @Parameter(
                            name = "loanNumber",
                            description = "The Loan number to search for",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Loan data found",
                            content = @Content(schema = @Schema(implementation = LoanData.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Loan not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid request")
            }
    )
    @GetMapping("/find")
    public ResponseEntity<List<LoanData>> find(@RequestParam String loanNumber) {
        List<LoanData> loanData = loanService.findLoan(loanNumber);
        HttpStatus status = loanData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(loanData);
    }

    @GetMapping("/findByMobileNumber")
    public ResponseEntity<LoanData> findByMobileNumber(@RequestParam String mobileNumber) {
        LoanData loanData = loanService.findByMobileNumber(mobileNumber);
        HttpStatus status = loanData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(loanData);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData> update(@Valid @RequestBody LoanData loanData) {
        boolean isSuccess = loanService.updateLoan(loanData);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();
        return ResponseEntity.status(status).body(body);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData> delete(@Valid @RequestBody LoanData loanData) {
        boolean isSuccess = loanService.deleteLoan(loanData);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseData body = ResponseData.builder()
                .statusCode(String.valueOf(status.value()))
                .message(status.getReasonPhrase())
                .build();
        return ResponseEntity.status(status).body(body);
    }
}
