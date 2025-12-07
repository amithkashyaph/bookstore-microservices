package com.microservices.bookstore.order_service.queues;

import com.microservices.bookstore.order_service.dtos.OrderCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {
    public void publish(OrderCreatedEvent orderCreatedEvent) {
    }
}
