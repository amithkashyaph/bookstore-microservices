package com.microservices.bookstore.order_service.queues;

import com.microservices.bookstore.order_service.configuration.ApplicationProperties;
import com.microservices.bookstore.order_service.dtos.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties applicationProperties;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate, ApplicationProperties applicationProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationProperties = applicationProperties;
    }
    public void publish(OrderCreatedEvent orderCreatedEvent) {
    }
}
