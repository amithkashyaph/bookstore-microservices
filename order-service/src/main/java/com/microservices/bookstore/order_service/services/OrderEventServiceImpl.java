package com.microservices.bookstore.order_service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.bookstore.order_service.dtos.OrderCancelledEvent;
import com.microservices.bookstore.order_service.dtos.OrderCreatedEvent;
import com.microservices.bookstore.order_service.dtos.OrderDeliveredEvent;
import com.microservices.bookstore.order_service.dtos.OrderErrorEvent;
import com.microservices.bookstore.order_service.entities.OrderEventEntity;
import com.microservices.bookstore.order_service.entities.enums.OrderEventType;
import com.microservices.bookstore.order_service.queues.OrderEventPublisher;
import com.microservices.bookstore.order_service.repositories.OrderEventRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderEventServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(OrderEventServiceImpl.class);
    private final OrderEventRepository orderEventRepository;
    private final OrderEventPublisher orderEventPublisher;

    private final ObjectMapper objectMapper;

    public OrderEventServiceImpl(
            OrderEventRepository orderEventRepository,
            OrderEventPublisher orderEventPublisher,
            ObjectMapper objectMapper) {
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

    void save(OrderDeliveredEvent event) {
        OrderEventEntity orderEvent = new OrderEventEntity();
        orderEvent.setEventId(event.eventId());
        orderEvent.setEventType(OrderEventType.ORDER_DELIVERED);
        orderEvent.setOrderNumber(event.orderNumber());
        orderEvent.setCreatedAt(event.createdAt());
        orderEvent.setPayload(toJsonPayload(event));
        this.orderEventRepository.save(orderEvent);
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
        log.info("Publishing events for {} orders", orderEventEntityList.size());
        for (OrderEventEntity orderEventEntity : orderEventEntityList) {
            this.publishEvent(orderEventEntity);
            orderEventRepository.delete(orderEventEntity);
        }
    }

    private void publishEvent(OrderEventEntity orderEventEntity) {
        OrderEventType orderEventType = orderEventEntity.getEventType();
        log.info("Publishing event of type {}", orderEventType);
        switch (orderEventType) {
            case ORDER_CREATED:
                OrderCreatedEvent orderCreatedEvent =
                        fromJsonPayload(orderEventEntity.getPayload(), OrderCreatedEvent.class);
                orderEventPublisher.publish(orderCreatedEvent);
                break;

            case ORDER_DELIVERED:
                OrderDeliveredEvent orderDeliveredEvent =
                        fromJsonPayload(orderEventEntity.getPayload(), OrderDeliveredEvent.class);
                orderEventPublisher.publish(orderDeliveredEvent);
                break;
            case ORDER_CANCELLED:
                OrderCancelledEvent orderCancelledEvent =
                        fromJsonPayload(orderEventEntity.getPayload(), OrderCancelledEvent.class);
                orderEventPublisher.publish(orderCancelledEvent);
                break;
            case ORDER_PROCESSING_FAILED:
                OrderErrorEvent orderErrorEvent = fromJsonPayload(orderEventEntity.getPayload(), OrderErrorEvent.class);
                orderEventPublisher.publish(orderErrorEvent);
                break;
            default:
                log.warn("Unsupported OrderEventType: {}", orderEventType);
        }
    }

    private <T> T fromJsonPayload(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
