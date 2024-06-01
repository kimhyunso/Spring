package com.example.chapter06.manyToMany.complexKeyDBVersionDomain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();
}
