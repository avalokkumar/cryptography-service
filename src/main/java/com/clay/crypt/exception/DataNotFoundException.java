package com.clay.crypt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
