package com.erp.integration.exception;

public class IntegrationException
        extends RuntimeException {

    public IntegrationException(
            String message) {

        super(message);
    }
}