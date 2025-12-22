package com.microservices.bookstore.order_service.controllers;

import com.microservices.bookstore.order_service.configuration.ApplicationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQDemoController {

    private RabbitTemplate rabbitTemplate;
    private ApplicationProperties applicationProperties;

    RabbitMQDemoController(RabbitTemplate rabbitTemplate, ApplicationProperties applicationProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationProperties = applicationProperties;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody MyMessage message) {
        rabbitTemplate.convertAndSend(
                applicationProperties.orderEventsExchange(), message.routingKey(), message.payload());
    }
}

record MyMessage(String routingKey, MyPayload payload) {}

record MyPayload(String content) {}
