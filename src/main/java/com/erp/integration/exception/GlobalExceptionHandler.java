package com.erp.integration.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidation(
            ValidationException ex) {

        return ResponseEntity.badRequest()
                .body(
                        Map.of(
                                "status",
                                "FAILED",
                                "message",
                                ex.getMessage()));
    }
}