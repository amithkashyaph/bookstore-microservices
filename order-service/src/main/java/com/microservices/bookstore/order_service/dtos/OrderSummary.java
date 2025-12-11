package com.microservices.bookstore.order_service.dtos;
import com.microservices.bookstore.order_service.entities.enums.OrderStatus;

public record OrderSummary(
        String orderNumber,
        OrderStatus orderStatus
) {
}
