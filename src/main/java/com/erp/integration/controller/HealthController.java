package com.erp.integration.controller;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @Operation(summary = "Check Application Health")
    @GetMapping("/api/health")
    public Map<String, String> checkHealth() {

        Map<String, String> response = new HashMap<>();

        response.put(
                "status",
                "SUCCESS");

        response.put(
                "message",
                "ERP Integration API Running");

        return response;
    }

    @Operation(summary = "Get Project Information")
    @GetMapping("/api/info")
    public Map<String, String> getProjectInfo() {

        Map<String, String> response = new HashMap<>();

        response.put(
                "project",
                "SAP ERP Financial Integration");

        response.put(
                "version",
                "1.0");

        response.put(
                "framework",
                "Spring Boot");

        return response;
    }
}