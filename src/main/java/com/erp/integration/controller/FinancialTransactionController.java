package com.erp.integration.controller;

import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.service.FinancialTransactionService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class FinancialTransactionController {

    private final FinancialTransactionService service;

    public FinancialTransactionController(
            FinancialTransactionService service) {

        this.service = service;
    }

    @Operation(summary = "Create Financial Transaction")
    @PostMapping
    public FinancialTransaction createTransaction(
            @RequestBody FinancialTransaction transaction) {

        return service.saveTransaction(transaction);
    }

    @Operation(summary = "Get All Financial Transactions")
    @GetMapping
    public List<FinancialTransaction> getTransactions() {

        return service.getAllTransactions();
    }
}