package com.erp.integration.integration;

import com.erp.integration.entity.FinancialTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ERPConnectorService {

    private static final Logger logger = LoggerFactory.getLogger(
            ERPConnectorService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    public FinancialTransaction fetchERPTransaction() {

        logger.info(
                "Starting ERP data extraction");

        String url = "http://localhost:8080/sap/api/financial-transaction";

        FinancialTransaction transaction = restTemplate.getForObject(
                url,
                FinancialTransaction.class);

        logger.info(
                "ERP transaction fetched: {}",
                transaction.getTransactionId());

        return transaction;
    }
}