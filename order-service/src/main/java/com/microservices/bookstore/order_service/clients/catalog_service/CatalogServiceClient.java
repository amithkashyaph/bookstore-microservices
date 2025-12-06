package com.microservices.bookstore.order_service.clients.catalog_service;

import com.microservices.bookstore.order_service.clients.catalog_service.dtos.ProductDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class CatalogServiceClient {
    private static final Logger log = LoggerFactory.getLogger(CatalogServiceClient.class);

    private final RestClient restClient;

    public CatalogServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Retry(name = "catalog-service")
    public Optional<ProductDto> getProductByCode(String code) {
        log.info("Fetching product for code: {}", code);

        var product = restClient
                .get()
                .uri("/api/products/{code}", code)
                .retrieve()
                .body(ProductDto.class);

        return Optional.ofNullable(product);
    }
}
