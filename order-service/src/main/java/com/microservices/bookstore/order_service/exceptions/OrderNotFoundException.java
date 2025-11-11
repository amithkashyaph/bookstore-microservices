package com.microservices.bookstore.order_service.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forOrderId(Long orderId) {
        return new OrderNotFoundException("Order with id: " + orderId + " does not exist");
    }
}
