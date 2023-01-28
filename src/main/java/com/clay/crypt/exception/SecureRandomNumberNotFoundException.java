package com.clay.crypt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SecureRandomNumberNotFoundException extends RuntimeException {

    public SecureRandomNumberNotFoundException() {
        super();
    }

    public SecureRandomNumberNotFoundException(String message) {
        super(message);
    }
}
