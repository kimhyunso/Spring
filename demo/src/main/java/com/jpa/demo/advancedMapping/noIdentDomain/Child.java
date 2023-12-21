package com.jpa.demo.advancedMapping.noIdentDomain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Child {

    @Id @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;


    // 연관관계 주인
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;


}
