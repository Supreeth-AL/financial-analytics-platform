package com.erp.integration.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    private static final Logger logger = LoggerFactory.getLogger(
            AuditService.class);

    public void logEvent(
            String event,
            String transactionId) {

        logger.info(
                "EVENT={} TRANSACTION_ID={}",
                event,
                transactionId);
    }
}