package com.jpa.demo.tableMapping2.domain;

import com.jpa.demo.tableMapping.domain.Team;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}
