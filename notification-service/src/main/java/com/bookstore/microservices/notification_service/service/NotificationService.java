package com.bookstore.microservices.notification_service.service;

import com.bookstore.microservices.notification_service.configuration.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final ApplicationProperties applicationProperties;
    private final JavaMailSender javaMailSender;

    public NotificationService(ApplicationProperties properties, JavaMailSender mailSender) {
        this.applicationProperties = properties;
        this.javaMailSender = mailSender;
    }
}
