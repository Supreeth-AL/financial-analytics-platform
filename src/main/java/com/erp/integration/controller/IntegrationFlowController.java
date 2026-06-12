package com.erp.integration.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.integration.audit.AuditService;
import com.erp.integration.dto.AnalyticsTransactionDTO;
import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.integration.AnalyticsPushService;
import com.erp.integration.integration.ERPConnectorService;
import com.erp.integration.service.TransformationService;
import com.erp.integration.service.ValidationService;

@RestController
public class IntegrationFlowController {

    private static final Logger logger = LoggerFactory.getLogger(
            IntegrationFlowController.class);

    private final ERPConnectorService connectorService;

    private final TransformationService transformationService;

    private final AnalyticsPushService analyticsPushService;

    private final ValidationService validationService;

    private final AuditService auditService;

    public IntegrationFlowController(
            ERPConnectorService connectorService,
            TransformationService transformationService,
            AnalyticsPushService analyticsPushService,
            ValidationService validationService,
            AuditService auditService) {

        this.connectorService = connectorService;
        this.transformationService = transformationService;
        this.analyticsPushService = analyticsPushService;
        this.validationService = validationService;
        this.auditService = auditService;
    }

    @GetMapping("/api/full-sync")
    public String fullSync() {

        // STEP 7: CORRELATION ID
        String correlationId = UUID.randomUUID().toString();

        logger.info(
                "Correlation ID: {}",
                correlationId);

        long startTime = System.currentTimeMillis();

        FinancialTransaction transaction = connectorService.fetchERPTransaction();

        auditService.logEvent(
                "ERP_FETCH_COMPLETED",
                transaction.getTransactionId());

        validationService.validate(transaction);

        auditService.logEvent(
                "VALIDATION_COMPLETED",
                transaction.getTransactionId());

        AnalyticsTransactionDTO dto = transformationService.transform(
                transaction);

        auditService.logEvent(
                "TRANSFORMATION_COMPLETED",
                transaction.getTransactionId());

        String response = analyticsPushService
                .pushToAnalyticsPlatform(dto);

        auditService.logEvent(
                "ANALYTICS_PUSH_COMPLETED",
                transaction.getTransactionId());

        long endTime = System.currentTimeMillis();

        logger.info(
                "Transaction {} processed in {} ms",
                transaction.getTransactionId(),
                (endTime - startTime));

        return response;
    }
}