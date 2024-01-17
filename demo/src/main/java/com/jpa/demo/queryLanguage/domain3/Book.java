package com.jpa.demo.queryLanguage.domain3;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;

@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item{

    private String author;
    private String isbn;
}
