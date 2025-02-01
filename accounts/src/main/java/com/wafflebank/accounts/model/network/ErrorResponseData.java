package com.wafflebank.accounts.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseData {
    private String path;
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
}
