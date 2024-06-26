package com.chatopbackend.chatopbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email already exists.")
public class EmailAlreadyInUseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}