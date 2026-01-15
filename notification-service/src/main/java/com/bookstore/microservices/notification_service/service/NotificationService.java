package com.bookstore.microservices.notification_service.service;

import com.bookstore.microservices.notification_service.configuration.ApplicationProperties;
import com.bookstore.microservices.notification_service.entities.OrderCancelledEvent;
import com.bookstore.microservices.notification_service.entities.OrderCreatedEvent;
import com.bookstore.microservices.notification_service.entities.OrderDeliveredEvent;
import com.bookstore.microservices.notification_service.entities.OrderErrorEvent;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final ApplicationProperties properties;
    private final JavaMailSender emailSender;

    public NotificationService(ApplicationProperties properties, JavaMailSender mailSender) {
        this.properties = properties;
        this.emailSender = mailSender;
    }
}
