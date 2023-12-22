package com.jpa.demo.advancedMapping.manyToOneTableMappingDomain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Child {

    @Id @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "PARENT_ID"))
    private Parent parent;

    public void setParent(Parent parent){

        if (parent != null){
            parent.getChildren().remove(this);
        }
        parent.getChildren().add(this);
        this.parent = parent;
    }


}
