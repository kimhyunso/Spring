package com.jpa.demo.advancedMapping.embeddedDomain;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Parent {

    @EmbeddedId
    private ParentId id;

    private String name;

}
