package com.bookstore.microservices.notification_service.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_events")
public class OrderEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String eventId;

    @Column(name = "created_at" ,nullable = false, updatable = false)
    private LocalDateTime createdAt =  LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrderEvent(String eventId) {
        this.eventId = eventId;
    }

    public OrderEvent() {}
}
