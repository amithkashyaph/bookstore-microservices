package com.microservices.bookstore.order_service.utils;

import com.microservices.bookstore.order_service.dtos.*;
import com.microservices.bookstore.order_service.entities.Order;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderEventMapper {
    public static OrderCreatedEvent buildOrderCreatedEvent(Order order) {
        return new OrderCreatedEvent(
                UUID.randomUUID().toString(),
                order.getOrderNumber(),
                getOrderItems(order),
                order.getCustomer(),
                order.getDeliveryAddress(),
                LocalDateTime.now());
    }

    public static OrderDeliveredEvent buildOrderDeliveredEvent(Order order) {
        return new OrderDeliveredEvent(
                UUID.randomUUID().toString(),
                order.getOrderNumber(),
                getOrderItems(order),
                order.getCustomer(),
                order.getDeliveryAddress(),
                LocalDateTime.now());
    }


}
