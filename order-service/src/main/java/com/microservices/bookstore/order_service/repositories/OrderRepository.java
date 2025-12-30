package com.microservices.bookstore.order_service.repositories;

import com.microservices.bookstore.order_service.dtos.OrderSummary;
import com.microservices.bookstore.order_service.entities.Order;
import com.microservices.bookstore.order_service.entities.enums.OrderStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus orderStatus);

    Optional<Order> findByOrderNumber(String orderNumber);

    default void updateOrderStatus(String orderNumber, OrderStatus status) {
        Order order = this.findByOrderNumber(orderNumber).orElseThrow();
        order.setStatus(status);
        this.save(order);
    }

    @Query(
            """
            select new com.microservices.bookstore.order_service.dtos.OrderSummary(o.orderNumber, o.status)
            from Order o
            where o.userName = :userName
            """)
    List<OrderSummary> findByUserName(String userName);

    @Query(
            """
            select distinct o
            from Order o left join fetch o.orderItems
            where o.userName = :userName and o.orderNumber = :orderNumber
            """)
    Optional<Order> findByUserNameAndOrderNumber(String userName, String orderNumber);
}
