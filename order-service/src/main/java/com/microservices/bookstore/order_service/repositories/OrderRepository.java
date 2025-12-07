package com.microservices.bookstore.order_service.repositories;

import com.microservices.bookstore.order_service.entities.Order;
import com.microservices.bookstore.order_service.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus orderStatus);
}
