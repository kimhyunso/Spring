package com.example.chapter14.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Parent {

    @Id @GeneratedValue
    private Long id;

    @OneToMany
    @JoinColumn
    private Collection<Member> collection = new ArrayList<>();

    @OneToMany
    @JoinColumn
    private List<Member> list = new ArrayList<>();


}
