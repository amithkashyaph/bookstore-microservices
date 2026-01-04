package com.microservices.bookstore.catalog_service.utils;

import com.microservices.bookstore.catalog_service.dtos.ProductResponseDTO;
import com.microservices.bookstore.catalog_service.entities.Product;

public class ProductEntityMapper {

    public static ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getImageUrl(),
                product.getPrice());
    }
}
