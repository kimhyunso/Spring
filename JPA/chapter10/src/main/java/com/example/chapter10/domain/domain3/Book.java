package com.example.chapter10.domain.domain3;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@DiscriminatorValue("B")
@Entity
public class Book extends Item{

    private String author;
    private String isbn;
}
