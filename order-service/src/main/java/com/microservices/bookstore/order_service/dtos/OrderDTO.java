package com.microservices.bookstore.order_service.dtos;

import com.microservices.bookstore.order_service.entities.embeddable_records.Address;
import com.microservices.bookstore.order_service.entities.embeddable_records.Customer;
import com.microservices.bookstore.order_service.entities.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderDTO(
        String orderNumber,
        String user,
        Set<OrderItemDto> items,
        Customer customer,
        Address deliveryAddress,
        OrderStatus status,
        String comments,
        LocalDateTime createdAt
) {
}
