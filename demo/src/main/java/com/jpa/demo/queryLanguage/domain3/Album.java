package com.jpa.demo.queryLanguage.domain3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;

@Getter
@Setter
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;
    private String etc;

}
