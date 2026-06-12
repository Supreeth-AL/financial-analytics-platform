package com.erp.integration.dto;

public class ErrorResponseDTO {

    private String status;
    private String errorCode;
    private String message;

    public ErrorResponseDTO(
            String status,
            String errorCode,
            String message) {

        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}