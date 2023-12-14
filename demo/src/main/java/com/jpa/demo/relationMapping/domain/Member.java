package com.jpa.demo.relationMapping.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    public Member(String username){
        this.username = username;
    }
    
    // 다대일 단방향 연관관계
    // 연관관계 주인
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



}
