package com.bodhisatta.smartorderinventory.service;

import com.bodhisatta.smartorderinventory.dto.request.OrderRequest;
import com.bodhisatta.smartorderinventory.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);

    List<OrderResponse> getOrdersByUser(Long userId);

}
