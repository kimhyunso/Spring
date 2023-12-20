package com.jpa.demo.advancedMapping.embeddedDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ParentId implements Serializable {
    @Column(name = "PARENT_ID1")
    private String id1;

    @Column(name = "PARENT_ID2")
    private String id2;


    public ParentId(){}

    public ParentId(String id1, String id2){this.id1 = id1; this.id2 = id2;}



    @Override
    public boolean equals(Object obj) {
        /*
        * ParentId id1 = new ParentId();
        * id1.setId1("myId1");
        * id1.setId2("myId2");
        *
        * ParentId id2 = new ParentId();
        * id2.setId1("myId1");
        * id2.setId2("myId2");
        *
        * id1.equals(id2) => ?;
        *
        * @Override => 참
        * @Override x => 거짓
        * 
        * 
        * equals => == (동일성비교)
        *
        *
        *
        *
        *
        *
        *
        *
        *
        * */


        return super.equals(obj);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
