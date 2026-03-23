package com.bodhisatta.smartorderinventory.controller;

import com.bodhisatta.smartorderinventory.dto.request.ProductRequest;
import com.bodhisatta.smartorderinventory.dto.response.ProductResponse;
import com.bodhisatta.smartorderinventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request)
    {
        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }

    @GetMapping
    public Page<ProductResponse> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "id") String sortBy)
    {
        return productService.getAllProducts(page, size, sortBy);
    }

//    public List<ProductResponse> getAllProducts()
//    {
//        return productService.getAllProducts();
//    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request)
    {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public Page<ProductResponse> searchProducts(@RequestParam String name, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size)
    {
        return productService.searchProducts(name, page, size);
    }

    @GetMapping("/price")
    public Page<ProductResponse> getProductsAbovePrice(@RequestParam Double price, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size)
    {
        return productService.getProductsAbovePrice(price, page, size);
    }

}
