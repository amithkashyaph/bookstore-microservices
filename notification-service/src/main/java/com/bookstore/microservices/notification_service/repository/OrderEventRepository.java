package com.bookstore.microservices.notification_service.repository;

import com.bookstore.microservices.notification_service.entities.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {

}
