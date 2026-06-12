package com.erp.integration.controller;

import com.erp.integration.dto.AnalyticsTransactionDTO;
import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.integration.AnalyticsPushService;
import com.erp.integration.integration.ERPConnectorService;
import com.erp.integration.service.TransformationService;
import com.erp.integration.service.ValidationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationFlowController {

    private final ERPConnectorService connectorService;

    private final TransformationService transformationService;

    private final AnalyticsPushService analyticsPushService;

    private final ValidationService validationService;

    public IntegrationFlowController(
            ERPConnectorService connectorService,
            TransformationService transformationService,
            AnalyticsPushService analyticsPushService,
            ValidationService validationService) {

        this.connectorService = connectorService;

        this.transformationService = transformationService;

        this.analyticsPushService = analyticsPushService;

        this.validationService = validationService;
    }

    @GetMapping("/api/full-sync")
    public String fullSync() {

        // Step 1: Fetch ERP Data
        FinancialTransaction transaction = connectorService.fetchERPTransaction();

        // Step 2: Validate ERP Data
        validationService.validate(transaction);

        // Step 3: Transform Data
        AnalyticsTransactionDTO dto = transformationService.transform(transaction);

        // Step 4: Send To Analytics Platform
        return analyticsPushService
                .pushToAnalyticsPlatform(dto);
    }
}