package com.microservices.bookstore.order_service.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    ApplicationProperties applicationProperties;

    public RabbitMQConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
}
