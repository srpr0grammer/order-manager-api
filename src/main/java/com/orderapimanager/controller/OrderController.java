package com.orderapimanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @GetMapping("/")
    public String getOrders() {
        return "ORDER TESTE";
    }
}
