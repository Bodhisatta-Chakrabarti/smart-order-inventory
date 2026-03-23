package com.bodhisatta.smartorderinventory.repository;

import com.bodhisatta.smartorderinventory.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price >= :price")
    Page<Product> findProductsAbovePrice(@Param("price") Double price, Pageable pageable);

}
