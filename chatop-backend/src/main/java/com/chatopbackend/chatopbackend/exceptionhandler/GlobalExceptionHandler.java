package com.chatopbackend.chatopbackend.exceptionhandler;

import com.chatopbackend.chatopbackend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<String> handleEmailAlreadyInUseException(EmailAlreadyInUseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleRentalNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<String> handleRentalNotFoundException(RentalNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(StringNotNumericException.class)
    public ResponseEntity<String> handleStringNotNumericException(StringNotNumericException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}