package com.microservices.bookstore.order_service.queues;

import com.microservices.bookstore.order_service.configuration.ApplicationProperties;
import com.microservices.bookstore.order_service.dtos.OrderCancelledEvent;
import com.microservices.bookstore.order_service.dtos.OrderCreatedEvent;
import com.microservices.bookstore.order_service.dtos.OrderDeliveredEvent;
import com.microservices.bookstore.order_service.dtos.OrderErrorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(OrderEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties applicationProperties;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate, ApplicationProperties applicationProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationProperties = applicationProperties;
    }

    public void publish(OrderCreatedEvent orderCreatedEvent) {
        log.info("routing key used {}", applicationProperties.newOrdersQueue());
        this.send(applicationProperties.newOrdersQueue(), orderCreatedEvent);
    }

    public void publish(OrderDeliveredEvent event) {
        this.send(applicationProperties.deliveredOrdersQueue(), event);
    }

}
