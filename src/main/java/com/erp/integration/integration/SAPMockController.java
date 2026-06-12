package com.erp.integration.integration;

import com.erp.integration.entity.FinancialTransaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SAPMockController {

    @GetMapping("/sap/api/financial-transaction")
    public FinancialTransaction getSAPTransaction() {

        FinancialTransaction transaction = new FinancialTransaction();

        transaction.setTransactionId("SAP1002");
        transaction.setInvoiceNumber("INV7001");
        transaction.setCustomerName("Infosys");
        transaction.setAmount(250000.0);
        transaction.setCurrency("INR");
        transaction.setStatus("PAID");

        return transaction;
    }
}