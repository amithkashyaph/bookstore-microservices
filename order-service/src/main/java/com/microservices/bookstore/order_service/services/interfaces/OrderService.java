package com.microservices.bookstore.order_service.services.interfaces;

import com.microservices.bookstore.order_service.dtos.CreateOrderRequest;
import com.microservices.bookstore.order_service.dtos.CreateOrderResponse;
import com.microservices.bookstore.order_service.dtos.OrderSummary;

import java.util.List;

public interface OrderService {
    CreateOrderResponse createOrder(String username, CreateOrderRequest createOrderRequest);
    List<OrderSummary> fetchAllOrdersForUser(String username);

}
