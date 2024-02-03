package com.example.chapter09.domain.domain3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zipcode {

    private String zip;
    private String plusFour;

}
