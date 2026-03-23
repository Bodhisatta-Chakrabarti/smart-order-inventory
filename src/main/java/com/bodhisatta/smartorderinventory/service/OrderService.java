package com.bodhisatta.smartorderinventory.service;

import com.bodhisatta.smartorderinventory.dto.request.OrderRequest;
import com.bodhisatta.smartorderinventory.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);

}
