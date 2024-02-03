package com.example.chapter07.domain.embeddedIdentDomain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
