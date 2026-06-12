package com.erp.integration.integration;

import com.erp.integration.dto.AnalyticsTransactionDTO;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsPlatformController {

    @PostMapping("/receive")
    public String receiveAnalyticsData(
            @RequestBody AnalyticsTransactionDTO dto) {

        System.out.println(
                "Analytics Platform Received: "
                        + dto.getTxnId());

        throw new RuntimeException(
                "Analytics Platform Down");
    }
}