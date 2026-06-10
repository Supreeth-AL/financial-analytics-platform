package com.erp.integration.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, String> checkHealth() {
        Map<String, String> response = new HashMap<>();

        response.put("status", "SUCCESS");
        response.put("message", "ERP Integreation API Running");

        return response;
    }

    @GetMapping("/api/info")
    public Map<String, String> getProjectInfo() {

        Map<String, String> response = new HashMap<>();

        response.put("project", "SAP ERP Financial Integration");

        response.put("version", "1.0");

        response.put("framework", "Spring Boot");

        return response;
    }
}
