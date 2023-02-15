package com.orderapimanager.service;

import com.orderapimanager.repository.ItemRepository;
import com.orderapimanager.repository.OrderRepository;
import com.orderapimanager.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;
    
//    public Order create (Order order) {
//        if (order)
//
//    }
}
