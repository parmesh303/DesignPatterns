package com.parmesh.prepare.interviews.microservicespattern.circuit.breaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CircuitBreakerService {

    private final RestTemplate restTemplate;
    private static final String SERVICE_NAME = "externalService";

    public CircuitBreakerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackMethod")
    @Retry(name = SERVICE_NAME)
    public String callExternalService(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    public String fallbackMethod(String url, Exception e) {
        return "Fallback response for URL: " + url + " due to: " + e.getMessage();
    }
} 