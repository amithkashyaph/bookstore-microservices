package com.microservices.bookstore.order_service.dtos;

import com.microservices.bookstore.order_service.entities.embeddable_records.Address;
import com.microservices.bookstore.order_service.entities.embeddable_records.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record CreateOrderRequest(
        @Valid @NotEmpty(message = "Items cannot be empty") Set<OrderItemDto> items,
        @Valid Customer customer,
        @Valid Address deliveryAddress) {}
