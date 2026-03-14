package com.bodhisatta.smartorderinventory.repository;

import com.bodhisatta.smartorderinventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
