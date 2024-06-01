package com.example.chapter14.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// @Convert(converter = BooleanToYNConverter.class, attributeName = "vip")
public class Member {

    @Id @GeneratedValue
    private Long id;
    @Column(name = "MEMBER_NAME")
    private String username;

    @ManyToOne
    private Team team;

    // @Convert(converter = BooleanToYNConverter.class)
    private boolean vip;


}
