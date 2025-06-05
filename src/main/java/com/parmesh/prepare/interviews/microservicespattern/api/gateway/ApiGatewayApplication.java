package com.parmesh.prepare.interviews.microservicespattern.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service Route
                .route("user_service", r -> r
                        .path("/api/users/**")
                        .filters(f -> f
                                .addRequestHeader("X-Request-Source", "gateway")
                                .addResponseHeader("X-Response-Source", "gateway")
                                .stripPrefix(1))
                        .uri("lb://USER-SERVICE"))
                
                // Order Service Route
                .route("order_service", r -> r
                        .path("/api/orders/**")
                        .filters(f -> f
                                .addRequestHeader("X-Request-Source", "gateway")
                                .addResponseHeader("X-Response-Source", "gateway")
                                .stripPrefix(1))
                        .uri("lb://ORDER-SERVICE"))
                
                // Product Service Route
                .route("product_service", r -> r
                        .path("/api/products/**")
                        .filters(f -> f
                                .addRequestHeader("X-Request-Source", "gateway")
                                .addResponseHeader("X-Response-Source", "gateway")
                                .stripPrefix(1))
                        .uri("lb://PRODUCT-SERVICE"))
                .build();
    }
} 