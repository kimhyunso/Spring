package com.example.chapter07.domain.oneToOneJoinTableMappingDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    // joinColumns : 현재 엔티티를 참조하는 외래 키
    // inverseJoinColumns : 반대방향 엔티티를 참조하는 외래 키
    @OneToOne
    @JoinTable(name = "PARENT_CHILD",
        joinColumns = @JoinColumn(name = "PARENT_ID"),
        inverseJoinColumns =  @JoinColumn(name = "CHILD_ID")
    )
    private Child child;

    // CHILD (PARENT) :: 단방향 => PARENT_CHILD <= PARENT (CHILD) :: 단방향 :: 양방향
    

}
