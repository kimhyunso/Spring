package com.jpa.demo.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album extends Item{
    private String artist;
    private String etc;


}
