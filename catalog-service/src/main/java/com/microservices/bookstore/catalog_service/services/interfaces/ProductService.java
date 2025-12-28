package com.microservices.bookstore.catalog_service.services.interfaces;

import com.microservices.bookstore.catalog_service.dtos.PagedResult;
import com.microservices.bookstore.catalog_service.dtos.ProductResponseDTO;

public interface ProductService {

    PagedResult<ProductResponseDTO> getAllProducts(int pageNo);

    ProductResponseDTO getProductByCode(String code);
}
