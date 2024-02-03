package com.example.chapter09.domain.domain1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int age;
}
