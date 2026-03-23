package com.bodhisatta.smartorderinventory.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {

    private Long orderId;
    private String productName;
    private Integer quantity;
    private Double totalAmount;

}
