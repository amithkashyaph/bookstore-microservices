package com.microservices.bookstore.order_service.jobs;

import com.microservices.bookstore.order_service.services.OrderServiceImpl;
import java.time.Instant;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingJob {
    private static final Logger log = LoggerFactory.getLogger(OrderProcessingJob.class);

    private final OrderServiceImpl orderService;

    public OrderProcessingJob(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "${orders.new-orders-job-cron}")
    @SchedulerLock(name = "processNewOrders")
    public void processNewOrders() {
        log.info("Processing new orders at {}", Instant.now());
        LockAssert.assertLocked();
        orderService.processNewOrders();
    }
}
