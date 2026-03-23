package com.bodhisatta.smartorderinventory.repository;

import com.bodhisatta.smartorderinventory.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
