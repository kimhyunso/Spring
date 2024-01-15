package com.jpa.demo.queryLanguage.domain2;

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
    private Long id; // 상태필드
    private String name; // 상태필드
    private int age; // 상태필드

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team; // 연관필드 (단일 값 연관 필드)
}
