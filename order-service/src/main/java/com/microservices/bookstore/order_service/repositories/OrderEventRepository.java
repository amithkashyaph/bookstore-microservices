package com.microservices.bookstore.order_service.repositories;

import com.microservices.bookstore.order_service.entities.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {
}
