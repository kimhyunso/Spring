package com.jpa.demo.domain.item;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends Item{

    private String director;
    private String actor;
}
