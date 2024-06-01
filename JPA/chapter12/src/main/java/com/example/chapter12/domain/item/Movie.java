package com.example.chapter12.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
