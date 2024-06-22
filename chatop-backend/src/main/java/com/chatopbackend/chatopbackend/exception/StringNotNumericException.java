package com.chatopbackend.chatopbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "String passed is not numeric.")
public class StringNotNumericException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public StringNotNumericException(String errorMessage) {
        super(errorMessage);
    }
}
