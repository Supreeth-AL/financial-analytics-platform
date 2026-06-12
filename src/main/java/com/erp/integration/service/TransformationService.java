package com.erp.integration.service;

import com.erp.integration.dto.AnalyticsTransactionDTO;
import com.erp.integration.entity.FinancialTransaction;
import com.erp.integration.mapper.TransactionMapper;

import org.springframework.stereotype.Service;

@Service
public class TransformationService {

    public AnalyticsTransactionDTO transform(FinancialTransaction transaction) {

        return TransactionMapper
                .convertToAnalyticsDTO(
                        transaction);
    }
}