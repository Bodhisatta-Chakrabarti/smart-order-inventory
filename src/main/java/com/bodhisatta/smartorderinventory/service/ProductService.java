package com.bodhisatta.smartorderinventory.service;

import com.bodhisatta.smartorderinventory.dto.request.ProductRequest;
import com.bodhisatta.smartorderinventory.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

}
