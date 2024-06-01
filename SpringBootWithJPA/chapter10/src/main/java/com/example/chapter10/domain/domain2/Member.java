package com.example.chapter10.domain.domain2;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@NamedQuery(
//        name = "Member.findByUsername",
//        query = "SELECT m FROM Member m WHERE m.name = :name"
//)
@NamedQueries({
        @NamedQuery(
                name = "Member.findByUsername",
                query = "SELECT m FROM Member m WHERE m.name = :name"
        ),
        @NamedQuery(
                name = "Member.count",
                query = "SELECT count(m) FROM Member m"
        )
})
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id; // 상태필드
    private String name; // 상태필드
    private int age; // 상태필드

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team; // 연관필드 (단일 값 연관 필드)
}
