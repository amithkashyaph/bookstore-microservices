package com.bookstore.microservices.notification_service.events;

import com.bookstore.microservices.notification_service.entities.OrderCancelledEvent;
import com.bookstore.microservices.notification_service.entities.OrderCreatedEvent;
import com.bookstore.microservices.notification_service.entities.OrderDeliveredEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("Order created event : " + event);
    }

    @RabbitListener(queues = "${notifications.delivered-orders-queue}")
    void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        System.out.println("Order delivered event : " + event);
    }

    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        System.out.println("Order cancelled event : " + event);
    }
}
