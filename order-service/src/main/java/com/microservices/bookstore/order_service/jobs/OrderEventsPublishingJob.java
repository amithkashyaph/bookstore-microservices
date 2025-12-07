package com.microservices.bookstore.order_service.jobs;

import com.microservices.bookstore.order_service.services.OrderEventServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsPublishingJob {
    private static final Logger log = LoggerFactory.getLogger(OrderEventsPublishingJob.class);

    private final OrderEventServiceImpl orderEventService;

    OrderEventsPublishingJob(OrderEventServiceImpl orderEventService) {
        this.orderEventService = orderEventService;
    }

}
