package com.erp.integration.mapper;

import com.erp.integration.dto.AnalyticsTransactionDTO;
import com.erp.integration.entity.FinancialTransaction;

public class TransactionMapper {

    public static AnalyticsTransactionDTO convertToAnalyticsDTO(
            FinancialTransaction transaction) {

        AnalyticsTransactionDTO dto = new AnalyticsTransactionDTO();

        dto.setTxnId(
                transaction.getTransactionId());

        dto.setInvoiceId(
                transaction.getInvoiceNumber());

        dto.setClientName(
                transaction.getCustomerName());

        dto.setRevenue(
                transaction.getAmount());

        dto.setCurrencyCode(
                transaction.getCurrency());

        dto.setPaymentStatus(
                transaction.getStatus());

        return dto;
    }
}