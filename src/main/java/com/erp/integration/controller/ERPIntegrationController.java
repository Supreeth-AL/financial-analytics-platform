package com.erp.integration.controller;

import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.integration.ERPConnectorService;
import com.erp.integration.service.FinancialTransactionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ERPIntegrationController {

    private final ERPConnectorService connectorService;

    private final FinancialTransactionService service;

    public ERPIntegrationController(
            ERPConnectorService connectorService,
            FinancialTransactionService service) {

        this.connectorService = connectorService;
        this.service = service;
    }

    @GetMapping("/api/sync")
    public FinancialTransaction syncERPData() {

        FinancialTransaction transaction = connectorService.fetchERPTransaction();

        return service.saveTransaction(transaction);
    }
}