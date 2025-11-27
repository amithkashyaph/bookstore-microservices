package com.microservices.bookstore.order_service.services;

import com.microservices.bookstore.order_service.dtos.CreateOrderRequest;
import com.microservices.bookstore.order_service.dtos.CreateOrderResponse;
import com.microservices.bookstore.order_service.services.interfaces.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public CreateOrderResponse createOrder(String username, CreateOrderRequest createOrderRequest) {
        return null;
    }
}
