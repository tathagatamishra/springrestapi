package com.example.springrestapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final long startTime = System.currentTimeMillis();

    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();

        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("uptime_seconds", (System.currentTimeMillis() - startTime) / 1000);
        response.put("java_version", System.getProperty("java.version"));
        response.put("memory", getMemoryInfo());

        return ResponseEntity.ok(response);
    }

    private Map<String, Object> getMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object> memory = new HashMap<>();
        memory.put("total_mb", runtime.totalMemory() / (1024 * 1024));
        memory.put("used_mb", (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
        memory.put("free_mb", runtime.freeMemory() / (1024 * 1024));
        return memory;
    }
}