package com.jpa.demo.advancedMapping.idClassIdentDomain;

import java.io.Serializable;

public class ChildId implements Serializable {

    private String parent;
    private String childId;

    public ChildId(){}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
