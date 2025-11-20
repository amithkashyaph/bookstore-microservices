package com.microservices.bookstore.order_service.entities;

import com.microservices.bookstore.order_service.entities.embeddable_records.Address;
import com.microservices.bookstore.order_service.entities.embeddable_records.Customer;
import com.microservices.bookstore.order_service.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(name = "username", nullable = false)
    private String userName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "name", column = @Column(name = "customer_name")),
                    @AttributeOverride(name = "email", column = @Column(name = "customer_email")),
                    @AttributeOverride(name = "phone", column = @Column(name = "customer_phone"))
            })
    private Customer customer;



}
