package com.microservices.bookstore.order_service.jobs;

import com.microservices.bookstore.order_service.services.OrderEventServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class OrderEventsPublishingJob {
    private static final Logger log = LoggerFactory.getLogger(OrderEventsPublishingJob.class);

    private final OrderEventServiceImpl orderEventService;

    public OrderEventsPublishingJob(OrderEventServiceImpl orderEventService) {
        this.orderEventService = orderEventService;
    }

    @Scheduled(cron = "${orders.publish-order-events-job-cron}")
    public void publishOrderEvents() {
        log.info("Publishing events at: {}", Instant.now());
        orderEventService.publishOrderEvents();
    }

}
