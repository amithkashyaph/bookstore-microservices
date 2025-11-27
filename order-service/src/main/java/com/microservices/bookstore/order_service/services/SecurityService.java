package com.microservices.bookstore.order_service.services;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public String getLoggedInUserName() {
        return "user";
    }
}
