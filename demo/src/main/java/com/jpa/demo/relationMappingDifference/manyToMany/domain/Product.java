package com.jpa.demo.relationMappingDifference.manyToMany.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;
}
