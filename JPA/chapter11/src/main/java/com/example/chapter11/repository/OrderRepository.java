package com.example.chapter11.repository;

import com.example.chapter11.domain.Order;
import com.example.chapter11.domain.OrderSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // List<Order> findAll(OrderSearch orderSearch);
}
