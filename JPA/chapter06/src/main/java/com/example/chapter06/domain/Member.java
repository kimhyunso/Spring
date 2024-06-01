package com.example.chapter06.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    public Member(String username){
        this.username = username;
    }
    

    // 연관관계 주인
    // 다대일 양방향 관계 설정
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;
//
//    public void setTeam(Team team){
//        this.team = team;
//
//        if (!team.getMembers().contains(this)){
//            team.getMembers().add(this);
//        }
//    }


    // 일대다 양방향 관계 설정
    // 일대다 단방향
    // insertable, updatable = false ==> 읽기전용
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

}
