package com.jpa.demo.valueType.domain3;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String city;
    private String state;

    @Embedded
    private Zipcode zipcode;

    public Address cloned() throws CloneNotSupportedException {
        Address newAddress = (Address) super.clone();
        return newAddress;
    }

}
