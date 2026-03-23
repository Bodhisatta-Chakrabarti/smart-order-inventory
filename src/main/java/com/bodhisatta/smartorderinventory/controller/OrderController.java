package com.bodhisatta.smartorderinventory.controller;

import com.bodhisatta.smartorderinventory.dto.request.OrderRequest;
import com.bodhisatta.smartorderinventory.dto.response.OrderResponse;
import com.bodhisatta.smartorderinventory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest request)
    {
        return orderService.placeOrder(request);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getUserOrders(@PathVariable Long userId)
    {
        return orderService.getOrdersByUser(userId);
    }

}
