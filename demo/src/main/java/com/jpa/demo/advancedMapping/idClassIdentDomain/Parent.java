package com.jpa.demo.advancedMapping.idClassIdentDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Parent {

    @Id @Column(name = "PARENT_ID")
    private String id;

    private String name;
}
