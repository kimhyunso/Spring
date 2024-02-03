package com.example.chapter07.domain.noIdentDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    

}
