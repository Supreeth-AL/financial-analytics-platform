package com.erp.integration.integration;

import com.erp.integration.dto.AnalyticsTransactionDTO;
import com.erp.integration.exception.IntegrationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalyticsPushService {

    private static final Logger logger = LoggerFactory.getLogger(
            AnalyticsPushService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    public String pushToAnalyticsPlatform(
            AnalyticsTransactionDTO dto) {

        String url = "http://localhost:8080/analytics/receive";

        int maxRetries = 3;

        int attempt = 0;

        while (attempt < maxRetries) {

            try {

                logger.info(
                        "Attempt {} sending transaction {}",
                        attempt + 1,
                        dto.getTxnId());

                String response = restTemplate.postForObject(
                        url,
                        dto,
                        String.class);

                logger.info(
                        "Analytics platform response: {}",
                        response);

                return response;

            } catch (Exception ex) {

                attempt++;

                logger.error(
                        "Attempt {} failed: {}",
                        attempt,
                        ex.getMessage());

                // STEP 5: WAIT 2 SECONDS BEFORE RETRY
                try {

                    logger.info(
                            "Waiting 2 seconds before retry...");

                    Thread.sleep(2000);

                } catch (InterruptedException e) {

                    Thread.currentThread()
                            .interrupt();

                    throw new IntegrationException(
                            "Retry interrupted");
                }

                if (attempt >= maxRetries) {

                    throw new IntegrationException(
                            "Analytics Platform Unavailable");
                }
            }
        }

        throw new IntegrationException(
                "Analytics Platform Unavailable");
    }
}