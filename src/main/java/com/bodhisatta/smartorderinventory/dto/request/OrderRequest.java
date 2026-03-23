package com.bodhisatta.smartorderinventory.dto.request;

import lombok.Data;

@Data
public class OrderRequest {

    private Long userId;
    private Long productId;
    private Integer quantity;

}
