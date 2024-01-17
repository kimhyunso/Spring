package com.jpa.demo.queryLanguage.domain3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;

@Getter
@Setter
@DiscriminatorValue("M")
public class Movie extends Item{

    private String director;
    private String actor;

}
