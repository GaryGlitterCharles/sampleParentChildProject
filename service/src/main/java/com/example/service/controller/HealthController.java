package com.example.service.controller;

import com.example.commons.util.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> health(HttpServletRequest request) {
        // Using commons utility to get request information
        String requestSummary = RequestUtils.getRequestSummary(request);
        boolean isLocal = RequestUtils.isLocalRequest(request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("request", requestSummary);
        response.put("isLocalRequest", isLocal);
        response.put("message", "Service is healthy");
        
        return ResponseEntity.ok(response);
    }
}

