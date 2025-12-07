package com.microservices.bookstore.order_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.bookstore.order_service.queues.OrderEventPublisher;
import com.microservices.bookstore.order_service.repositories.OrderEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderEventServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(OrderEventServiceImpl.class);
    private final OrderEventRepository orderEventRepository;
    private final OrderEventPublisher orderEventPublisher;

    private final ObjectMapper objectMapper;

    public OrderEventServiceImpl(OrderEventRepository orderEventRepository, OrderEventPublisher orderEventPublisher, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.orderEventPublisher = orderEventPublisher;
        this.orderEventRepository = orderEventRepository;
    }

}
