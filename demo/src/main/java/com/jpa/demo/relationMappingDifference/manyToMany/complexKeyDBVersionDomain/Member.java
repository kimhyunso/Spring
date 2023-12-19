package com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDBVersionDomain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

}
