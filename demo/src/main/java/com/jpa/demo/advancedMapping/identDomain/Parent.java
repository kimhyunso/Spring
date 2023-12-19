package com.jpa.demo.advancedMapping.identDomain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(ParentId.class)
@Getter
@Setter
public class Parent {

    @Id @Column(name = "PARENT_ID1")
    private String id1;

    @Id @Column(name = "PARENT_ID2")
    private String id2;

    private String name;
}
