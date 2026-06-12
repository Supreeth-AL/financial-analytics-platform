package com.erp.integration.service;

import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.exception.ValidationException;
import com.erp.integration.repository.FinancialTransactionRepository;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private final FinancialTransactionRepository repository;

    public ValidationService(
            FinancialTransactionRepository repository) {

        this.repository = repository;
    }

    public void validate(
            FinancialTransaction transaction) {

        validateTransactionId(transaction);

        validateAmount(transaction);

        validateCurrency(transaction);

        validateStatus(transaction);

        validateDuplicate(transaction);
    }

    private void validateTransactionId(
            FinancialTransaction transaction) {

        if (transaction.getTransactionId() == null
                || transaction.getTransactionId().isBlank()) {

            throw new ValidationException(
                    "Transaction ID cannot be empty");
        }
    }

    private void validateAmount(
            FinancialTransaction transaction) {

        if (transaction.getAmount() == null
                || transaction.getAmount() <= 0) {

            throw new ValidationException(
                    "Amount must be greater than zero");
        }
    }

    private void validateCurrency(
            FinancialTransaction transaction) {

        if (transaction.getCurrency() == null
                || transaction.getCurrency().isBlank()) {

            throw new ValidationException(
                    "Currency is required");
        }
    }

    private void validateStatus(
            FinancialTransaction transaction) {

        String status = transaction.getStatus();

        if (!"PAID".equals(status)
                && !"PENDING".equals(status)) {

            throw new ValidationException(
                    "Invalid transaction status");
        }
    }

    private void validateDuplicate(
            FinancialTransaction transaction) {

        if (repository.findByTransactionId(
                transaction.getTransactionId())
                .isPresent()) {

            throw new ValidationException(
                    "Duplicate Transaction Found");
        }
    }
}