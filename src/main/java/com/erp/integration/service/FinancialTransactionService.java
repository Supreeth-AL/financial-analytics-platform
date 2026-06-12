package com.erp.integration.service;

import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.repository.FinancialTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialTransactionService {

    private final FinancialTransactionRepository repository;

    public FinancialTransactionService(
            FinancialTransactionRepository repository) {

        this.repository = repository;
    }

    public FinancialTransaction saveTransaction(
            FinancialTransaction transaction) {

        return repository.save(transaction);
    }

    public List<FinancialTransaction> getAllTransactions() {

        return repository.findAll();
    }
}