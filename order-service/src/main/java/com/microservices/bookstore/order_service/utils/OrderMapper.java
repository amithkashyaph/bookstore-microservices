package com.microservices.bookstore.order_service.utils;

import com.microservices.bookstore.order_service.dtos.CreateOrderRequest;
import com.microservices.bookstore.order_service.dtos.OrderDTO;
import com.microservices.bookstore.order_service.dtos.OrderItemDto;
import com.microservices.bookstore.order_service.entities.Order;
import com.microservices.bookstore.order_service.entities.OrderItem;
import com.microservices.bookstore.order_service.entities.enums.OrderStatus;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order convertToEntity(CreateOrderRequest request) {
        Order newOrder = new Order();
        newOrder.setOrderNumber(UUID.randomUUID().toString());
        newOrder.setStatus(OrderStatus.NEW);
        newOrder.setCustomer(request.customer());
        newOrder.setDeliveryAddress(request.deliveryAddress());
        Set<OrderItem> orderItems = new HashSet<>();
        for (OrderItemDto item : request.items()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCode(item.code());
            orderItem.setName(item.name());
            orderItem.setPrice(item.price());
            orderItem.setQuantity(item.quantity());
            orderItem.setOrder(newOrder);
            orderItems.add(orderItem);
        }
        newOrder.setOrderItems(orderItems);
        return newOrder;
    }

    public static OrderDTO convertToDTO(Order order) {
        Set<OrderItemDto> orderItems = order.getOrderItems().stream()
                .map(item -> new OrderItemDto(item.getCode(), item.getName(), item.getPrice(), item.getQuantity()))
                .collect(Collectors.toSet());

        return new OrderDTO(
                order.getOrderNumber(),
                order.getUserName(),
                orderItems,
                order.getCustomer(),
                order.getDeliveryAddress(),
                order.getStatus(),
                order.getComments(),
                order.getCreatedAt());
    }
}
