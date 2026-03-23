package com.bodhisatta.smartorderinventory.service.impl;

import com.bodhisatta.smartorderinventory.dto.request.OrderRequest;
import com.bodhisatta.smartorderinventory.dto.response.OrderResponse;
import com.bodhisatta.smartorderinventory.entity.Order;
import com.bodhisatta.smartorderinventory.entity.Product;
import com.bodhisatta.smartorderinventory.entity.User;
import com.bodhisatta.smartorderinventory.exception.ResourceNotFoundException;
import com.bodhisatta.smartorderinventory.repository.OrderRepository;
import com.bodhisatta.smartorderinventory.repository.ProductRepository;
import com.bodhisatta.smartorderinventory.repository.UserRepository;
import com.bodhisatta.smartorderinventory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        User user=userRepository.findById(request.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found"));

        Product product=productRepository.findById(request.getProductId()).orElseThrow(()->new ResourceNotFoundException("Product not found"));

        //Business logic: Check stock
        if (product.getQuantity()< request.getQuantity())
        {
            throw new RuntimeException("Insufficient stock");
        }

        //Reduce stock
        product.setQuantity(product.getQuantity()- request.getQuantity());
        Double totalAmount= product.getPrice()* request.getQuantity();

        Order order=Order.builder().user(user).quantity(request.getQuantity()).totalAmount(totalAmount)
                .createdAt(LocalDateTime.now()).build();

        orderRepository.save(order);

        return OrderResponse.builder().orderId(order.getId()).productName(product.getName())
                .quantity(order.getQuantity()).totalAmount(order.getTotalAmount()).build();

    }

}
