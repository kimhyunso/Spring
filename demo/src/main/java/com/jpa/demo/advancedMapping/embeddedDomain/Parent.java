package com.jpa.demo.advancedMapping.embeddedDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Parent {

    @EmbeddedId
    private ParentId id;

    private String name;

}
