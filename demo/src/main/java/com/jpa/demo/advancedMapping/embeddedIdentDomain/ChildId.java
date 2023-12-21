package com.jpa.demo.advancedMapping.embeddedIdentDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

import java.io.Serializable;


@Embeddable
public class ChildId implements Serializable {


    // @MapsId로 매핑
    private String parentId;

    @Column(name = "CHILD_ID")
    private String id;

    public ChildId(){}

    public ChildId(String id){
        this.id = id;
    }



    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
