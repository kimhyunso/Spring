package com.example.chapter06.oneToOne.domain;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;


//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;



    @OneToOne(mappedBy = "member")
    private Locker locker;


}
