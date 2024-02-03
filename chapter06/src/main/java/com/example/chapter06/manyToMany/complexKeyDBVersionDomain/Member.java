package com.example.chapter06.manyToMany.complexKeyDBVersionDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

}
