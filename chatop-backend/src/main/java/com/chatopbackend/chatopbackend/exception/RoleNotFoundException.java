package com.chatopbackend.chatopbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Role is not found.")
public class RoleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RoleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
