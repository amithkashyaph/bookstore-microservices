package com.microservices.bookstore.order_service.controllers;

import com.microservices.bookstore.order_service.configuration.ApplicationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQDemoController {

    private RabbitTemplate rabbitTemplate;
    private ApplicationProperties applicationProperties;


    RabbitMQDemoController(RabbitTemplate rabbitTemplate, ApplicationProperties applicationProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationProperties = applicationProperties;
    }
}
