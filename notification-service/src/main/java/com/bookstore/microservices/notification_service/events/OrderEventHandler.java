package com.bookstore.microservices.notification_service.events;

import com.bookstore.microservices.notification_service.entities.OrderCancelledEvent;
import com.bookstore.microservices.notification_service.entities.OrderCreatedEvent;
import com.bookstore.microservices.notification_service.entities.OrderDeliveredEvent;
import com.bookstore.microservices.notification_service.entities.OrderErrorEvent;
import com.bookstore.microservices.notification_service.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    private final NotificationService notificationService;

    public OrderEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("Order created event : " + event);
        notificationService.sendOrderCreatedNotification(event);
    }

    @RabbitListener(queues = "${notifications.delivered-orders-queue}")
    void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        System.out.println("Order delivered event : " + event);
        notificationService.sendOrderDeliveredNotification(event);
    }

    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        System.out.println("Order cancelled event : " + event);
        notificationService.sendOrderCancelledNotification(event);
    }

    @RabbitListener(queues = "${notifications.error-orders-queue}")
    void handleOrderErrorEvent(OrderErrorEvent event) {
        System.out.println("Order error event : " + event);
        notificationService.sendOrderErrorEventNotification(event);
    }
}
