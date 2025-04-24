package com.wafflebank.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileExistException extends RuntimeException {

    public FileExistException(String message) {
        super(message);
    }
}
