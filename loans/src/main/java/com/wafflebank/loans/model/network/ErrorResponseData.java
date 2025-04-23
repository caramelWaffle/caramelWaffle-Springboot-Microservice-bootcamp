package com.wafflebank.loans.model.network;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Standard structure for API error responses")
public class ErrorResponseData {

    @Schema(
            description = "Request path where the error occurred",
            example = "/api/find"
    )
    private String path;

    @Schema(
            description = "HTTP status of the error",
            example = "NOT_FOUND"
    )
    private HttpStatus status;

    @Schema(
            description = "Descriptive error message",
            example = "Account not found for the given account number"
    )
    private String message;

    @Schema(
            description = "Timestamp when the error occurred",
            example = "2025-04-18T10:15:30"
    )
    private LocalDateTime timestamp;
}
