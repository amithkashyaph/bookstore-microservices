package com.microservices.bookstore.catalog_service.controllers;

import com.microservices.bookstore.catalog_service.dtos.PagedResult;
import com.microservices.bookstore.catalog_service.dtos.ProductResponseDTO;
import com.microservices.bookstore.catalog_service.services.interfaces.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<ProductResponseDTO> getAllProducts(@RequestParam(name = "page", defaultValue = "1") int pageNumber) {
        return productService.getAllProducts(pageNumber);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductResponseDTO> getProductByCode(@PathVariable String code) {
        log.info("Fetching product for code: {}", code);
        ProductResponseDTO productResponse = productService.getProductByCode(code);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    void sleep() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
