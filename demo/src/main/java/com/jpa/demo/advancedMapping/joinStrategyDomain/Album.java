package com.jpa.demo.advancedMapping.joinStrategyDomain;

import com.jpa.demo.advancedMapping.singleStrategyDomain.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}


