package com.microservices.bookstore.order_service.services;

import com.microservices.bookstore.order_service.dtos.CreateOrderRequest;
import com.microservices.bookstore.order_service.dtos.CreateOrderResponse;
import com.microservices.bookstore.order_service.dtos.OrderCreatedEvent;
import com.microservices.bookstore.order_service.entities.Order;
import com.microservices.bookstore.order_service.repositories.OrderRepository;
import com.microservices.bookstore.order_service.services.interfaces.OrderService;
import com.microservices.bookstore.order_service.utils.OrderEventMapper;
import com.microservices.bookstore.order_service.utils.OrderMapper;
import com.microservices.bookstore.order_service.utils.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OrderEventServiceImpl orderEventService;

    OrderServiceImpl(OrderRepository orderRepository, OrderValidator orderValidator, OrderEventServiceImpl orderEventService) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.orderEventService = orderEventService;
    }
    @Override
    public CreateOrderResponse createOrder(String username, CreateOrderRequest createOrderRequest) {
        orderValidator.validate(createOrderRequest);
        Order newOrder = OrderMapper.convertToEntity(createOrderRequest);
        newOrder.setUserName(username);
        Order savedOrder = orderRepository.save(newOrder);

        log.info("Created order with orderNumber = {}", savedOrder.getOrderNumber());

        OrderCreatedEvent orderCreatedEvent = OrderEventMapper.buildOrderCreatedEvent(savedOrder);
        orderEventService.save(orderCreatedEvent);

        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }
}
