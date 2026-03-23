package com.bodhisatta.smartorderinventory.controller;

import com.bodhisatta.smartorderinventory.dto.request.OrderRequest;
import com.bodhisatta.smartorderinventory.dto.response.OrderResponse;
import com.bodhisatta.smartorderinventory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
