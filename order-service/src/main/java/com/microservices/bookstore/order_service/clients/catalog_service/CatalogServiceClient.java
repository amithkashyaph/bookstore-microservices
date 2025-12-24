package com.microservices.bookstore.order_service.clients.catalog_service;

import com.microservices.bookstore.order_service.clients.catalog_service.dtos.ProductDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogServiceClient {
    private static final Logger log = LoggerFactory.getLogger(CatalogServiceClient.class);

    private final RestClient restClient;

    public CatalogServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getByProductCodeFallback")
    public Optional<ProductDto> getProductByCode(String code) {
        log.info("Fetching product for code: {}", code);

        var product =
                restClient.get().uri("/api/products/{code}", code).retrieve().body(ProductDto.class);

        return Optional.ofNullable(product);
    }

    Optional<ProductDto> getByProductCodeFallback(String code, Throwable t) {
        log.error("Catalog Service client failure for code: {}", code);
        return Optional.empty();
    }
}
