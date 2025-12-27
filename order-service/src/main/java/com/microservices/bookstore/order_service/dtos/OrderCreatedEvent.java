package com.microservices.bookstore.order_service.dtos;

import com.microservices.bookstore.order_service.entities.embeddable_records.Address;
import com.microservices.bookstore.order_service.entities.embeddable_records.Customer;
import java.time.LocalDateTime;
import java.util.Set;

public record OrderCreatedEvent(
        String eventId,
        String orderNumber,
        Set<OrderItemDto> items,
        Customer customer,
        Address deliveryAddress,
        LocalDateTime createdAt) {}
