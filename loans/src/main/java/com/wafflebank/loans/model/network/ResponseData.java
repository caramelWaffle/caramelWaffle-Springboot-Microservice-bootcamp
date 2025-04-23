package com.wafflebank.loans.model.network;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Standard API response structure containing status and message")
public class ResponseData {

    @Schema(
            description = "HTTP status code of the response, represented as a string",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Descriptive message about the result of the API operation",
            example = "Operation successful"
    )
    private String message;
}