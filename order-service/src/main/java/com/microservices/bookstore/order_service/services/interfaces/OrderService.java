package com.microservices.bookstore.order_service.services.interfaces;

import com.microservices.bookstore.order_service.dtos.CreateOrderRequest;
import com.microservices.bookstore.order_service.dtos.CreateOrderResponse;

public interface OrderService {
    CreateOrderResponse createOrder(String username, CreateOrderRequest createOrderRequest);

}
