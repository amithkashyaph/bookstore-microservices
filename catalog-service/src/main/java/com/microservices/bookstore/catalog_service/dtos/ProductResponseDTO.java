package com.microservices.bookstore.catalog_service.dtos;

import java.math.BigDecimal;

public record ProductResponseDTO(String code, String name, String description, String imageUrl, BigDecimal price) {}
