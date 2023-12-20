package com.jpa.demo.advancedMapping.idClassIdentDomain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(GrandChildId.class)
@Getter
@Setter
public class GrandChild {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child child;



    @Id @Column(name = "GRANDCHILD_ID")
    private String id;

    private String name;
}
