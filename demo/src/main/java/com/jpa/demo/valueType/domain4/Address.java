package com.jpa.demo.valueType.domain4;

import com.jpa.demo.valueType.domain3.Zipcode;
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
public class Address{

    private String street;
    private String city;
    private String state;


}
