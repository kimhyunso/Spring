package com.jpa.demo.relationMapping.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;


    public Team(String name){
        this.name = name;
    }

    // 다대일, 일대다 양방향 연관관계
    // 일대다 엔티티를 하나 이상 참조할 수 있으므로, Collection, List, Set 등 중 하나를 사용해야함
    // 연관관계 주인 x
//    @OneToMany(mappedBy = "team")
//    private List<Member> members = new ArrayList<>();
//
//    public void addMember(Member member){
//        this.members.add(member);
//
//        if (member.getTeam() != this){
//            member.setTeam(this);
//        }
//    }
    
    // 단방향
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<Member>();


}
