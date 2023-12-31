package com.jpa.demo.tableMappingInteractive.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;



    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void setTeam(Team team) {
        // 기존 팀과의 관계 제거
        if (this.team != null){
            team.getMembers().remove(this);
        }

        team.getMembers().add(this);
        this.team = team;
    }
}
