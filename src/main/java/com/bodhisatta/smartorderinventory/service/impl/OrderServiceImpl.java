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
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public static final Logger log=LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        try{

            User user=userRepository.findById(request.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
            log.info("Placing order for user: {}", request.getUserId());

            Product product=productRepository.findById(request.getProductId()).orElseThrow(()->new ResourceNotFoundException("Product not found"));
            log.info("Product ID: {}, Quantity: {}", request.getProductId(), request.getQuantity());

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

        } catch (ObjectOptimisticLockingFailureException ex) {
            log.error("Concurrent update detected", ex);
            throw new RuntimeException("Concurrent update detected. Please try again.");
        }

    }

    @Override
    public List<OrderResponse> getOrdersByUser(Long userId) {

        return orderRepository.findByUserId(userId).stream().map(order -> OrderResponse.builder()
                .orderId(order.getId()).quantity(order.getQuantity()).totalAmount(order.getTotalAmount())
                .productName("N/A").build()).toList();

    }

}
