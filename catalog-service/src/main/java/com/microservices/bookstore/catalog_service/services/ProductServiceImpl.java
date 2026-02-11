package com.microservices.bookstore.catalog_service.services;

import com.microservices.bookstore.catalog_service.config.ApplicationProperties;
import com.microservices.bookstore.catalog_service.dtos.PagedResult;
import com.microservices.bookstore.catalog_service.dtos.ProductResponseDTO;
import com.microservices.bookstore.catalog_service.entities.Product;
import com.microservices.bookstore.catalog_service.exceptions.ProductNotFoundException;
import com.microservices.bookstore.catalog_service.repositories.ProductRepository;
import com.microservices.bookstore.catalog_service.services.interfaces.ProductService;
import com.microservices.bookstore.catalog_service.utils.ProductEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ApplicationProperties applicationProperties;

    public ProductServiceImpl(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }


}
