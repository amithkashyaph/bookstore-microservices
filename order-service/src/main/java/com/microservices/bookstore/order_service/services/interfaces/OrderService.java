package com.microservices.bookstore.order_service.services.interfaces;

import com.microservices.bookstore.order_service.dtos.*;
import java.util.List;

public interface OrderService {
    CreateOrderResponse createOrder(String username, CreateOrderRequest createOrderRequest);

    List<OrderSummary> fetchAllOrdersForUser(String username);

    OrderDTO fetchOrderInfoForUserAndOrderNumber(String username, String orderNumber);
}
