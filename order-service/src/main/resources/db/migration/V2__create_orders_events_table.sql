SET SESSION auto_increment_increment = 50;
SET SESSION auto_increment_offset = 1;

CREATE TABLE order_events (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_number VARCHAR(100) NOT NULL,
    event_id VARCHAR(255) UNIQUE NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    payload TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_order_events_order_number FOREIGN KEY (order_number) REFERENCES orders(order_number)
);