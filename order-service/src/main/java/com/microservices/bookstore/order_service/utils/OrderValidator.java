package com.microservices.bookstore.order_service.utils;

import com.microservices.bookstore.order_service.clients.catalog_service.CatalogServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);

    private final CatalogServiceClient catalogServiceClient;

    public OrderValidator(CatalogServiceClient catalogServiceClient) {
        this.catalogServiceClient = catalogServiceClient;
    }
}
