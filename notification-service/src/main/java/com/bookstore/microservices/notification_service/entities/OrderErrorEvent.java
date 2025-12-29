package com.bookstore.microservices.notification_service.entities;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderErrorEvent(
        String eventId,
        String orderNumber,
        Set<OrderItemDto> items,
        Customer customer,
        Address deliveryAddress,
        String reason,
        LocalDateTime createdAt
) {
}
