package com.bodhisatta.smartorderinventory.service.impl;

import com.bodhisatta.smartorderinventory.dto.request.ProductRequest;
import com.bodhisatta.smartorderinventory.dto.response.ProductResponse;
import com.bodhisatta.smartorderinventory.entity.Product;
import com.bodhisatta.smartorderinventory.exception.ResourceNotFoundException;
import com.bodhisatta.smartorderinventory.repository.ProductRepository;
import com.bodhisatta.smartorderinventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        Product product=Product.builder().name(request.getName()).description(request.getDescription())
                .price(request.getPrice()).quantity(request.getQuantity()).build();

        product=productRepository.save(product);

        return mapToResponse(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found: " + id));

        return mapToResponse(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(int page, int size, String sortBy)
    {
        Pageable pageable= PageRequest.of(page, size, Sort.by(sortBy));

        return productRepository.findAll(pageable).map(this::mapToResponse);
    }

//    public List<ProductResponse> getAllProducts() {
//
//        return productRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
//
//    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        productRepository.save(product);

        return mapToResponse(product);

    }

    @Override
    public void deleteProduct(Long id) {

        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found: " + id));

        productRepository.delete(product);

    }

    @Override
    public Page<ProductResponse> searchProducts(String name, int page, int size)
    {
        Pageable pageable=PageRequest.of(page, size);

        return productRepository.findByNameContainingIgnoreCase(name, pageable).map(this::mapToResponse);
    }

    @Override
    public Page<ProductResponse> getProductsAbovePrice(Double price, int page, int size) {

        Pageable pageable=PageRequest.of(page, size);

        return productRepository.findProductsAbovePrice(price, pageable).map(this::mapToResponse);

    }

    private ProductResponse mapToResponse(Product product) {

        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription())
                .price(product.getPrice()).quantity(product.getQuantity()).build();

    }

}
