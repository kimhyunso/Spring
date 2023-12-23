package com.jpa.demo.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
