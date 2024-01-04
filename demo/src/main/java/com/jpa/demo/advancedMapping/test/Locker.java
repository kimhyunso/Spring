package com.jpa.demo.advancedMapping.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Locker{
    @Id
    @Column(name = "LOCKER_ID")
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;
}