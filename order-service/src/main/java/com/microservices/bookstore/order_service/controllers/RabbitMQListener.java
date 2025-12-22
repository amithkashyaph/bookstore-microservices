package com.microservices.bookstore.order_service.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = "${orders.new-orders-queue}")
    public void handleNewOrders(MyPayload payload) {
        System.out.println("New order: " + payload.content());
    }

    @RabbitListener(queues = "${orders.delivered-orders-queue}")
    public void handleDeliveredOrders(MyPayload payload) {
        System.out.println("Order delivered: " + payload.content());
    }
}
