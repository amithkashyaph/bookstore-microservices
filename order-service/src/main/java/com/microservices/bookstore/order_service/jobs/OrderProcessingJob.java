package com.microservices.bookstore.order_service.jobs;

import com.microservices.bookstore.order_service.services.OrderEventServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingJob {
    private static final Logger log = LoggerFactory.getLogger(OrderProcessingJob.class);

    private final OrderEventServiceImpl orderEventService;

    public OrderProcessingJob(OrderEventServiceImpl orderEventService) {
        this.orderEventService = orderEventService;
    }

}
