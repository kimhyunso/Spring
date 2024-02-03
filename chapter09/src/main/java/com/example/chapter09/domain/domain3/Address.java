package com.example.chapter09.domain.domain3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Cloneable{

    // immutable object
    // String, Integer
    private String street;
    private String city;
    private String state;

    @Embedded
    private Zipcode zipcode;

    public Address(String state, String city, String street){
        this.state = state;
        this.city = city;
        this.street = street;
    }

    @Override
    public Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
