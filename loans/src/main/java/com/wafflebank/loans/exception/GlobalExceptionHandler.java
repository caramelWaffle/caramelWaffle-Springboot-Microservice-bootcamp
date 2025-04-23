package com.wafflebank.loans.exception;

import com.wafflebank.loans.model.network.ErrorResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileExistException.class)
    public ResponseEntity<ErrorResponseData> handleFileExistException(
            FileExistException ex,
            WebRequest request
    ) {
        ErrorResponseData errorResponseData = ErrorResponseData.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponseData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseData> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request
    ) {
        ErrorResponseData errorResponseData = ErrorResponseData.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponseData, HttpStatus.NOT_FOUND);
    }
}
