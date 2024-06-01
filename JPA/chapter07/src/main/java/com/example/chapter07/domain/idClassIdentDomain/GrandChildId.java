package com.example.chapter07.domain.idClassIdentDomain;

import java.io.Serializable;

public class GrandChildId implements Serializable {

    private ChildId child;
    private String id;

    public GrandChildId(){}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
