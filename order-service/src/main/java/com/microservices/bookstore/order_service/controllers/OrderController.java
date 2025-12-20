package com.microservices.bookstore.order_service.controllers;

import com.microservices.bookstore.order_service.dtos.CreateOrderRequest;
import com.microservices.bookstore.order_service.dtos.CreateOrderResponse;
import com.microservices.bookstore.order_service.dtos.OrderSummary;
import com.microservices.bookstore.order_service.services.interfaces.OrderService;
import com.microservices.bookstore.order_service.services.SecurityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;
    private SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        String username = securityService.getLoggedInUserName();
        log.info("Creating order for user: {}", username);
        CreateOrderResponse createOrderResponse = orderService.createOrder(username, createOrderRequest);
        return new ResponseEntity<>(createOrderResponse, HttpStatus.CREATED);
    }



}
