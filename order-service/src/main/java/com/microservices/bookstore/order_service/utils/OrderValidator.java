package com.microservices.bookstore.order_service.utils;

import com.microservices.bookstore.order_service.clients.catalog_service.CatalogServiceClient;
import com.microservices.bookstore.order_service.clients.catalog_service.dtos.ProductDto;
import com.microservices.bookstore.order_service.dtos.CreateOrderRequest;
import com.microservices.bookstore.order_service.dtos.OrderItemDto;
import com.microservices.bookstore.order_service.exceptions.InvalidOrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderValidator {

    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);

    private final CatalogServiceClient catalogServiceClient;

    public OrderValidator(CatalogServiceClient catalogServiceClient) {
        this.catalogServiceClient = catalogServiceClient;
    }

    public void validate(CreateOrderRequest createOrderRequest) {
        Set<OrderItemDto> items = createOrderRequest.items();
        for (OrderItemDto orderItemDto: items) {
            ProductDto productDto = catalogServiceClient.getProductByCode(orderItemDto.code())
                    .orElseThrow(() -> new InvalidOrderException("Invalid product code: " + orderItemDto.code()));
            if (orderItemDto.price().compareTo(productDto.price()) != 0) {
                log.error(
                        "Product price not matching. Actual price: {}, received price: {}",
                        productDto.price(),
                        orderItemDto.price()
                );
                throw new InvalidOrderException("Product price not matching");
            }
        }
    }
}
