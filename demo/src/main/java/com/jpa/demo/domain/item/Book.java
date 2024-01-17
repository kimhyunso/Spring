package com.jpa.demo.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book extends Item{

    private String author;
    private String isbn;

}
