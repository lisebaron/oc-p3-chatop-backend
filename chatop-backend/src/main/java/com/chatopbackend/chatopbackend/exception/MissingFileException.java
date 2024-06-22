package com.chatopbackend.chatopbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The uploaded file is required but is missing.")
public class MissingFileException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public MissingFileException(String message) {
        super(message);
    }
}