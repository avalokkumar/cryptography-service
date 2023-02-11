package com.clay.crypt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlgorithmNotSupported extends RuntimeException {

    public AlgorithmNotSupported() {
        super();
    }

    public AlgorithmNotSupported(String message) {
        super(message);
    }
}
