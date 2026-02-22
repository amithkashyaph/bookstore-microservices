package com.bookstore.microservices.notification_service.events;

import com.bookstore.microservices.notification_service.entities.*;
import com.bookstore.microservices.notification_service.repository.OrderEventRepository;
import com.bookstore.microservices.notification_service.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    private final static Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

    private final NotificationService notificationService;

    private final OrderEventRepository repository;

    public OrderEventHandler(NotificationService notificationService, OrderEventRepository orderEventRepository) {
        this.notificationService = notificationService;
        this.repository = orderEventRepository;
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Order created event : " + event);
        if (repository.existsByEventId(event.eventId())) {
            log.warn("Duplicate OrderCreatedEvent eventId received for publishing mail : " + event.eventId());
            return;
        }
        notificationService.sendOrderCreatedNotification(event);
        OrderEvent orderEvent = new OrderEvent(event.eventId());
        repository.save(orderEvent);
    }

    @RabbitListener(queues = "${notifications.delivered-orders-queue}")
    void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        log.info("Order delivered event : " + event);
        if (repository.existsByEventId(event.eventId())) {
            log.warn("Duplicate OrderDeliveredEvent eventId received for publishing mail : " + event.eventId());
            return;
        }
        notificationService.sendOrderDeliveredNotification(event);
        OrderEvent orderEvent = new OrderEvent(event.eventId());
        repository.save(orderEvent);
    }

    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        log.info("Order cancelled event : " + event);
        if (repository.existsByEventId(event.eventId())) {
            log.warn("Duplicate OrderCancelledEvent eventId received for publishing mail : " + event.eventId());
            return;
        }
        notificationService.sendOrderCancelledNotification(event);
        OrderEvent orderEvent = new OrderEvent(event.eventId());
        repository.save(orderEvent);
    }

    @RabbitListener(queues = "${notifications.error-orders-queue}")
    void handleOrderErrorEvent(OrderErrorEvent event) {
        System.out.println("Order error event : " + event);
        if (repository.existsByEventId(event.eventId())) {
            log.warn("Duplicate OrderErrorEvent eventId received for publishing mail : " + event.eventId());
            return;
        }
        notificationService.sendOrderErrorEventNotification(event);
        OrderEvent orderEvent = new OrderEvent(event.eventId());
        repository.save(orderEvent);
    }
}
