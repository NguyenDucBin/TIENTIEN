package com.example.Evara_Shop.repository;

import com.example.Evara_Shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
