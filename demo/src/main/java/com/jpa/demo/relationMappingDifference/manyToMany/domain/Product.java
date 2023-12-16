package com.jpa.demo.relationMappingDifference.manyToMany.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;


    @ManyToMany(mappedBy = "products")
    private List<Member> members;

}
