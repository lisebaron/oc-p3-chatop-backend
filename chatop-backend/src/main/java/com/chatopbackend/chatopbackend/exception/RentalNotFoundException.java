package com.chatopbackend.chatopbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Rental Not Found")
public class RentalNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RentalNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
