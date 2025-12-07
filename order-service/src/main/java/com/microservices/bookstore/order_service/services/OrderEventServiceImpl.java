package com.microservices.bookstore.order_service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.bookstore.order_service.dtos.OrderCreatedEvent;
import com.microservices.bookstore.order_service.entities.OrderEventEntity;
import com.microservices.bookstore.order_service.entities.enums.OrderEventType;
import com.microservices.bookstore.order_service.queues.OrderEventPublisher;
import com.microservices.bookstore.order_service.repositories.OrderEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    void save(OrderCreatedEvent event) {
        OrderEventEntity orderEventEntity = new OrderEventEntity();
        orderEventEntity.setEventId(event.eventId());
        orderEventEntity.setEventType(OrderEventType.ORDER_CREATED);
        orderEventEntity.setOrderNumber(event.orderNumber());
        orderEventEntity.setCreatedAt(event.createdAt());
        orderEventEntity.setPayload(toJsonPayload(event));

        orderEventRepository.save(orderEventEntity);
    }

    private String toJsonPayload(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void publishOrderEvents() {
        Sort sort = Sort.by("createdAt").ascending();
        List<OrderEventEntity> orderEventEntityList = orderEventRepository.findAll(sort);
        for(OrderEventEntity orderEventEntity: orderEventEntityList) {
            this.publishEvent(orderEventEntity);
            orderEventRepository.delete(orderEventEntity);
        }
    }

    private void publishEvent(OrderEventEntity orderEventEntity) {
    }

}
