package com.pureandcold.aggregator.controller;

import com.pureandcold.aggregator.annotation.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    // Public endpoint with IP-based rate limiting only (100 requests per minute)
    @GetMapping("/public")
    @RateLimit(authenticatedOnly = false, limit = 100, duration = 60)
    public String publicEndpoint() {
        return "Public endpoint";
    }

    // Authenticated endpoint with both IP and client-based rate limiting
    @GetMapping("/authenticated")
    @RateLimit(authenticatedOnly = true, limit = 50, duration = 60)
    public String authenticatedEndpoint() {
        return "Authenticated endpoint";
    }
} 