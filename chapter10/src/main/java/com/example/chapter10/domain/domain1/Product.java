package com.example.chapter10.domain.domain1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

    private int price;
    private int stockAmount;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();



    public void setUpdateStockAmount(int stockAmount){
        this.stockAmount -= stockAmount;
    }

    public void updateStockAmount(int stockAmount){
        this.stockAmount -= stockAmount;
    }
}
