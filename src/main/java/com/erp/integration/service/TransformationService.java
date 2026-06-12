package com.erp.integration.service;

import com.erp.integration.dto.AnalyticsTransactionDTO;
import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.mapper.TransactionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransformationService {

    private static final Logger logger = LoggerFactory.getLogger(
            TransformationService.class);

    public AnalyticsTransactionDTO transform(
            FinancialTransaction transaction) {

        logger.info(
                "Transforming transaction {}",
                transaction.getTransactionId());

        AnalyticsTransactionDTO dto = TransactionMapper.convertToAnalyticsDTO(
                transaction);

        logger.info(
                "Transformation completed for transaction {}",
                transaction.getTransactionId());

        return dto;
    }
}