package com.microservices.bookstore.order_service.services;

import com.microservices.bookstore.order_service.dtos.*;
import com.microservices.bookstore.order_service.entities.Order;
import com.microservices.bookstore.order_service.entities.enums.OrderStatus;
import com.microservices.bookstore.order_service.exceptions.OrderNotFoundException;
import com.microservices.bookstore.order_service.repositories.OrderRepository;
import com.microservices.bookstore.order_service.services.interfaces.OrderService;
import com.microservices.bookstore.order_service.utils.OrderEventMapper;
import com.microservices.bookstore.order_service.utils.OrderMapper;
import com.microservices.bookstore.order_service.utils.OrderValidator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final List<String> DELIVERY_ALLOWED_COUNTRIES = List.of("INDIA", "USA", "GERMANY", "UK");
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OrderEventServiceImpl orderEventService;

    OrderServiceImpl(
            OrderRepository orderRepository, OrderValidator orderValidator, OrderEventServiceImpl orderEventService) {
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

    @Override
    public List<OrderSummary> fetchAllOrdersForUser(String username) {
        List<OrderSummary> orders = orderRepository.findByUserName(username);
        return orders;
    }

    @Override
    public OrderDTO fetchOrderInfoForUserAndOrderNumber(String username, String orderNumber) {
        Order orderDetails = orderRepository
                .findByUserNameAndOrderNumber(username, orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for orderNumber " + orderNumber));
        OrderDTO orderDTO = OrderMapper.convertToDTO(orderDetails);
        return orderDTO;
    }

    public void processNewOrders() {
        List<Order> orders = orderRepository.findByStatus(OrderStatus.NEW);
        log.info("Found {} new orders to process", orders.size());
        for (Order order : orders) {
            this.process(order);
        }
    }

    private void process(Order order) {
        try {
            if (canBeDelivered(order)) {
                log.info("OrderNumber: {} can be delivered", order.getOrderNumber());
                orderRepository.updateOrderStatus(order.getOrderNumber(), OrderStatus.DELIVERED);
                orderEventService.save(OrderEventMapper.buildOrderDeliveredEvent(order));

            } else {
                log.info("OrderNumber: {} can not be delivered", order.getOrderNumber());
                orderRepository.updateOrderStatus(order.getOrderNumber(), OrderStatus.CANCELLED);
                orderEventService.save(
                        OrderEventMapper.buildOrderCancelledEvent(order, "Can't deliver to the location"));
            }
        } catch (RuntimeException e) {
            log.error("Failed to process Order with orderNumber: {}", order.getOrderNumber(), e);
            orderRepository.updateOrderStatus(order.getOrderNumber(), OrderStatus.ERROR);
            orderEventService.save(OrderEventMapper.buildOrderErrorEvent(order, e.getMessage()));
        }
    }

    private boolean canBeDelivered(Order order) {
        return DELIVERY_ALLOWED_COUNTRIES.contains(
                order.getDeliveryAddress().country().toUpperCase());
    }
}
