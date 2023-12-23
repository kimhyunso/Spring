package com.jpa.demo.proxy.Domain1;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @ManyToOne
    private Team team;

}
