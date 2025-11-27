package com.microservices.bookstore.order_service.exceptions;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(String message) {
        super(message);
    }
}
