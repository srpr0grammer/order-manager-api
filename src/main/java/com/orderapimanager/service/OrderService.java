package com.orderapimanager.service;

import com.orderapimanager.models.Item;
import com.orderapimanager.models.Order;
import com.orderapimanager.models.StockMovement;
import com.orderapimanager.models.User;
import com.orderapimanager.models.dto.OrderRequest;
import com.orderapimanager.models.dto.OrderRequestUp;
import com.orderapimanager.repository.ItemRepository;
import com.orderapimanager.repository.OrderRepository;
import com.orderapimanager.repository.StockMovementRepository;
import com.orderapimanager.repository.UserRepository;
import com.orderapimanager.service.exception.DataIntegrityViolationException;
import com.orderapimanager.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender javaMailSender;
    
    public Order createOrder(OrderRequest orderRequest) {
        var order = new Order();
        order.setQuantidade(orderRequest.getQuantidade());
        order.setCreatedAt(LocalDateTime.now());

        var userId = getUserById(orderRequest.getUserId());
        var itemId = getItemById(orderRequest.getItemId());

        var stockQuantity = getStockQuantityForItem(itemId);
        if (stockQuantity < orderRequest.getQuantidade()) {
            throw new DataIntegrityViolationException("Insufficient stock for item " + itemId.getId() + ".");
        }

        order.setUser(userId);
        order.setItem(itemId);
        orderRepository.save(order);
        sendOrderConfirmationEmail(order);

        var stockMovement = new StockMovement();
        stockMovement.setItem(itemId);
        stockMovement.setCreatedAt(LocalDateTime.now());
        stockMovement.setQuantity(orderRequest.getQuantidade());
        stockMovementRepository.save(stockMovement);

        return order;
    }
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                        () -> new ObjectNotFoundException("Order not found"));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    private Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Item not found"));
    }

    public Order updateOrder(OrderRequestUp orderRequestUp) {
        getById(orderRequestUp.getId());

        var order = new Order();
        order.setQuantidade(orderRequestUp.getQuantidade());

        return orderRepository.save(order);
    }

    public void delete (Long id) {
        getById(id);
        orderRepository.deleteById(id);
    }

    private Integer getStockQuantityForItem(Item item) {
        var stockMovements = stockMovementRepository.findByItemOrderByCreatedAtAsc(item);
        int totalStockQuantity = 0;
        for (StockMovement movement : stockMovements) {
            if (movement.getCreatedAt().isBefore(LocalDateTime.now())) {
                totalStockQuantity += movement.getQuantity();
            }
        }
        return totalStockQuantity;
    }

    private void sendOrderConfirmationEmail(Order order) {
        var message = new SimpleMailMessage();
        message.setTo(order.getUser().getEmail());
        message.setSubject("Order Confirmation");
        message.setText("Dear " + order.getUser().getName() + ",\n\nYour order has been confirmed.\n\nOrder details:\n\n" + order.toString());
        javaMailSender.send(message);
    }


}
