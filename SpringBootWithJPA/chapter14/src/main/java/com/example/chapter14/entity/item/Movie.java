package com.example.chapter14.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("M")
@Entity
@Getter
@Setter
public class Movie extends Item{
    private String director;
    private String actor;
}
