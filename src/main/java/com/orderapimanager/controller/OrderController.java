package com.orderapimanager.controller;

import com.orderapimanager.models.dto.OrderRequest;
import com.orderapimanager.models.dto.OrderResponse;
import com.orderapimanager.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<OrderResponse> orderCreate(@RequestBody OrderRequest orderRequest) {
        var order =
                orderService.createOrder(orderRequest);
        var orderResponse = modelMapper.map(order, OrderResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
