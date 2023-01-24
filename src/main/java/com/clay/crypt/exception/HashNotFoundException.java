package com.clay.crypt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HashNotFoundException extends RuntimeException {

    public HashNotFoundException() {
        super();
    }

    public HashNotFoundException(String message) {
        super(message);
    }
}
