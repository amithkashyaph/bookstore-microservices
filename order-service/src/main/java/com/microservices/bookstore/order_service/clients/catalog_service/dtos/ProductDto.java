package com.microservices.bookstore.order_service.clients.catalog_service.dtos;

import java.math.BigDecimal;

public record ProductDto(
        String code,
        String name,
        String description,
        String imageUrl,
        BigDecimal price
) {
}
