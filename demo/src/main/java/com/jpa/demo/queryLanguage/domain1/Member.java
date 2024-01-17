package com.jpa.demo.queryLanguage.domain1;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "NAME")
    private String username;

    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void setTeam(Team team){

        if (team.getMembers() != null){
            team.getMembers().remove(this);
        }

        this.team = team;
        this.team.getMembers().add(this);
    }

    public void setOrder(Order order){
        if (order.getMembers() != null){
            order.getMembers().remove(this);
        }
        this.order = order;
        this.order.getMembers().add(this);
    }


}
