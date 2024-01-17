package com.jpa.demo.advancedMapping.idClassIdentDomain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(GrandChildId.class)
@Getter
@Setter
public class GrandChild {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "CHILD_ID"),
            @JoinColumn(name = "PARENT_ID")
    })
    private Child child;



    @Id @Column(name = "GRANDCHILD_ID")
    private String id;

    private String name;
}
