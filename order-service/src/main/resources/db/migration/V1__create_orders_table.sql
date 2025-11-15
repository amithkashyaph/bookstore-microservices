SET @@auto_increment_increment = 50;
SET @@auto_increment_offset = 1;

CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_number VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    customer_phone VARCHAR(50) NOT NULL,
    delivery_address_line1 VARCHAR(255) NOT NULL,
    delivery_address_line2 VARCHAR(255),
    delivery_address_city VARCHAR(255) NOT NULL,
    delivery_address_state VARCHAR(255) NOT NULL,
    delivery_address_zip_code VARCHAR(50) NOT NULL,
    delivery_address_country VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL,
    comments TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
