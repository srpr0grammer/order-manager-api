package com.orderapimanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    public String getOrders() {
        return "ORDER TESTE";
    }
}
