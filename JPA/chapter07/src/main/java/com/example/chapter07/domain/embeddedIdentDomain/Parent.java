package com.example.chapter07.domain.embeddedIdentDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Parent {
    @Id
    @Column(name = "PARENT_ID")
    private String id;

    private String name;
}
