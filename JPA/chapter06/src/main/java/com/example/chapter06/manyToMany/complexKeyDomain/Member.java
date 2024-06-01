package com.example.chapter06.manyToMany.complexKeyDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;


    // 역방향
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberProduct> memberProducts;


}
