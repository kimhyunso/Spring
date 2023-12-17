package com.jpa.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> items = new ArrayList<>();


}
