package com.jpa.demo.queryLanguage.domain1;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@SqlResultSetMapping(
//        name = "memberWithOrderCount",
//        entities = {@EntityResult(entityClass = Member.class)},
//        columns = {@ColumnResult(name = "ORDER_COUNT")}
//)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(
        name = "Member.memberSQL",
        query = "SELECT m FROM Member m WHERE age > :age"
)
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

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public void setTeam(Team team){

        if (team.getMembers() != null){
            team.getMembers().remove(this);
        }

        this.team = team;
        this.team.getMembers().add(this);
    }


}
