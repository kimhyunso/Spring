package com.example.chapter07.domain.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Locker{
    @Id
    @Column(name = "LOCKER_ID")
    @GeneratedValue
    private Long id;

    private String name;

//    @OneToOne(mappedBy = "locker")
//    private Member member;
}