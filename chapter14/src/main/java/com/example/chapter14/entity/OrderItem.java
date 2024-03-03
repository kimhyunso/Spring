package com.example.chapter14.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

@Entity
public class OrderItem {
    private Long id;
    private int orderPrice;
    private int count;
}
