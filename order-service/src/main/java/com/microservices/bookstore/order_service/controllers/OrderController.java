package com.microservices.bookstore.order_service.controllers;

import com.microservices.bookstore.order_service.dtos.*;
import com.microservices.bookstore.order_service.services.SecurityService;
import com.microservices.bookstore.order_service.services.interfaces.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<OrderSummary>> getAllOrdersForUser() {
        String username = securityService.getLoggedInUserName();
        log.info("Getting all orders for user: {}", username);
        List<OrderSummary> orderSummaries = orderService.fetchAllOrdersForUser(username);
        return ResponseEntity.ok(orderSummaries);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderDTO> getByOrderNumber(@PathVariable(value = "orderNumber") String orderNumber) {
        log.info("Fetching order info for order number: {}", orderNumber);
        String username = securityService.getLoggedInUserName();
        OrderDTO orderDTO = orderService.fetchOrderInfoForUserAndOrderNumber(username, orderNumber);
        return ResponseEntity.ok(orderDTO);
    }
}
