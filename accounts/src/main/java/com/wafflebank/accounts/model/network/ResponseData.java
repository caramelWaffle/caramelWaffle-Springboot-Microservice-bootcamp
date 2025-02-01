package com.wafflebank.accounts.model.network;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor
public class ResponseData {
    private String message;
    private HttpStatus statusCode;
}
