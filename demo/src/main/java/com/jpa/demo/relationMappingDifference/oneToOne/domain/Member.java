package com.jpa.demo.relationMappingDifference.oneToOne.domain;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;


//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;



    @OneToOne(mappedBy = "member")
    private Locker locker;


}
