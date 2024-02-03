package com.example.chapter09.domain.domain2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name = "city")
    private String city;
    private String street;
    private String zipcode;

}
