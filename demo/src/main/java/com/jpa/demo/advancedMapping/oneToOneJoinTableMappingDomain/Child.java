package com.jpa.demo.advancedMapping.oneToOneJoinTableMappingDomain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;

    
    // 양방향 매핑
    @OneToOne(mappedBy = "child")
    private Parent parent;

}
