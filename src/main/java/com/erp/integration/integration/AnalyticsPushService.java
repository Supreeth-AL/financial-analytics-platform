package com.erp.integration.integration;

import com.erp.integration.dto.AnalyticsTransactionDTO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalyticsPushService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String pushToAnalyticsPlatform(
            AnalyticsTransactionDTO dto) {

        String url = "http://localhost:8080/analytics/receive";

        return restTemplate.postForObject(
                url,
                dto,
                String.class);
    }
}