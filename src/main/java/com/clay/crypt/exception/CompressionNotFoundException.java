package com.clay.crypt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CompressionNotFoundException extends RuntimeException {

    public CompressionNotFoundException() {
        super();
    }

    public CompressionNotFoundException(String message) {
        super(message);
    }
}
