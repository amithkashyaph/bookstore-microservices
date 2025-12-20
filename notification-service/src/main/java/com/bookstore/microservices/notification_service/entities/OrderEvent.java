package com.bookstore.microservices.notification_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_events")
public class OrderEvent {
    @Id
    private Long id;
    private String eventId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
