package com.example.chapter06.manyToMany.complexKeyDomain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MemberProductId implements Serializable {

    private String member;
    private String product;

    public MemberProductId(){}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
