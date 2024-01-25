package com.jpa.demo.queryLanguage.domain3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@DiscriminatorValue("A")
@Entity
public class Album extends Item{

    private String artist;
    private String etc;

}
