package com.jpa.demo.tableMappingInteractive.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Table(name = "TEAM")
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;


    // 양뱡향 연관관계 매핑
    @OneToMany(mappedBy = "team")
    private final List<Member> members = new ArrayList<Member>();

}
