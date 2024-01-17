package com.jpa.demo.advancedMapping.embeddedIdentDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Child {

    @EmbeddedId
    private ChildId id;

    
    // childId.parentId 매핑
    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    private String name;
}
