package com.example.chapter14.listener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;

@Entity
@EntityListeners(DuckListener.class)
public class Duck2 {
}
