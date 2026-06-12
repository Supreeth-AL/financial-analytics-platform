package com.erp.integration.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erp.integration.dto.ErrorResponseDTO;

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

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<ErrorResponseDTO> handleIntegrationException(
            IntegrationException ex) {

        return ResponseEntity
                .internalServerError()
                .body(
                        new ErrorResponseDTO(
                                "FAILED",
                                "INTEGRATION_ERROR",
                                ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(
            Exception ex) {

        return ResponseEntity
                .internalServerError()
                .body(
                        new ErrorResponseDTO(
                                "FAILED",
                                "SYSTEM_ERROR",
                                ex.getMessage()));
    }
}