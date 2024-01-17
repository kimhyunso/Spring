package com.jpa.demo.advancedMapping.idClassIdentDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(ChildId.class)
@Getter
@Setter
public class Child {

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;

    @Id @Column(name = "CHILD_ID")
    private String childId;

    private String name;
}
