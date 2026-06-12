package com.erp.integration.controller;

import com.erp.integration.dto.AnalyticsTransactionDTO;
import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.integration.AnalyticsPushService;
import com.erp.integration.integration.ERPConnectorService;
import com.erp.integration.service.TransformationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationFlowController {

    private final ERPConnectorService connectorService;

    private final TransformationService transformationService;

    private final AnalyticsPushService analyticsPushService;

    public IntegrationFlowController(
            ERPConnectorService connectorService,
            TransformationService transformationService,
            AnalyticsPushService analyticsPushService) {

        this.connectorService = connectorService;
        this.transformationService = transformationService;
        this.analyticsPushService = analyticsPushService;
    }

    @GetMapping("/api/full-sync")
    public String fullSync() {

        FinancialTransaction transaction =
                connectorService.fetchERPTransaction();

        AnalyticsTransactionDTO dto =
                transformationService.transform(transaction);

        return analyticsPushService
                .pushToAnalyticsPlatform(dto);
    }
}