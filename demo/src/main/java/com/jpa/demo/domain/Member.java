package com.jpa.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String city;

    private String street;
    private String zipcode;

    // 연관관계 주인 x
    // Order랑 일대다관계
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

}
