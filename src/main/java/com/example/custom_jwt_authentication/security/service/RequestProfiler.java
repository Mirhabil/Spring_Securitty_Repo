package com.example.custom_jwt_authentication.security.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RequestProfiler {

    private final Map<String, List<RequestLog>> logs = new ConcurrentHashMap<>();

    public void log(String username, String endpoint, String ip) {
        logs.putIfAbsent(username, new ArrayList<>());
        logs.get(username).add(new RequestLog(endpoint, ip, LocalDateTime.now()));
    }

    public boolean isSuspicious(String username) {
        List<RequestLog> userLogs = logs.get(username);
        if (userLogs == null || userLogs.size() < 5) return false;

        long lastMinute = userLogs.stream()
                .filter(log -> log.time().isAfter(LocalDateTime.now().minusMinutes(1)))
                .count();

        return lastMinute > 20; // Example: too many requests
    }

    public record RequestLog(String endpoint, String ip, LocalDateTime time) {}
}

