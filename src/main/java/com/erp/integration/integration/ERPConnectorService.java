package com.erp.integration.integration;

import com.erp.integration.entity.FinancialTransaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ERPConnectorService {

    private final RestTemplate restTemplate = new RestTemplate();

    public FinancialTransaction fetchERPTransaction() {

        String url = "http://localhost:8080/sap/api/financial-transaction";

        return restTemplate.getForObject(
                url,
                FinancialTransaction.class);
    }
}