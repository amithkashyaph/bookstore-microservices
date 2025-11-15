package com.microservices.bookstore.order_service.configuration;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final RabbitAdmin rabbitAdmin;

    public StartupRunner(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @Override
    public void run(String... args) {
        rabbitAdmin.initialize(); // forces declaration
    }
}
