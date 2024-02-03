package com.example.chapter11.domain.complexKey;

import java.io.Serializable;

// 복합키
public class CategoryItemId implements Serializable{


    private Long categoryId;


    private Long itemId;

    public CategoryItemId(){}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
