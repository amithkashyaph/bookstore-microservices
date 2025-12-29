package com.microservices.bookstore.order_service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservices.bookstore.order_service.entities.embeddable_records.Address;
import com.microservices.bookstore.order_service.entities.embeddable_records.Customer;
import com.microservices.bookstore.order_service.entities.enums.OrderStatus;
import java.math.BigDecimal;
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
        LocalDateTime createdAt) {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
