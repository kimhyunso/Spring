package com.jpa.demo.queryLanguage.domain1;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "NAME")
    private String username;

    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void setTeam(Team team){

        if (team.getMembers() != null){
            team.getMembers().remove(this.team);
        }

        this.team = team;
        this.team.getMembers().add(this);
    }
}
