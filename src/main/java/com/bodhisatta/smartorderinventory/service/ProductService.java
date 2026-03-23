package com.bodhisatta.smartorderinventory.service;

import com.bodhisatta.smartorderinventory.dto.request.ProductRequest;
import com.bodhisatta.smartorderinventory.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(Long id);

    //List<ProductResponse> getAllProducts();
    Page<ProductResponse> getAllProducts(int page, int size, String sortBy);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

    Page<ProductResponse> searchProducts(String name, int page, int size);

    Page<ProductResponse> getProductsAbovePrice(Double price, int page, int size);

}
