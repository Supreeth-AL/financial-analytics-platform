package com.erp.integration.controller;

import com.erp.integration.dto.AnalyticsTransactionDTO;
import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.integration.ERPConnectorService;
import com.erp.integration.service.TransformationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {

    private final ERPConnectorService connectorService;

    private final TransformationService transformationService;

    public AnalyticsController(
            ERPConnectorService connectorService,
            TransformationService transformationService) {

        this.connectorService = connectorService;

        this.transformationService = transformationService;
    }

    @GetMapping("/api/analytics-data")
    public AnalyticsTransactionDTO getAnalyticsData() {

        FinancialTransaction transaction = connectorService
                .fetchERPTransaction();

        return transformationService
                .transform(transaction);
    }
}   