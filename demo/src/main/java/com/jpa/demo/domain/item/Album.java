package com.jpa.demo.domain.item;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
