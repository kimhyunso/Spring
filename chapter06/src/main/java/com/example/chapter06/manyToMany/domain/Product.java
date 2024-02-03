package com.example.chapter06.manyToMany.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;


//    @ManyToMany(mappedBy = "products")
//    private List<Member> members;

}
