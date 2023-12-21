package com.jpa.demo.advancedMapping.embeddedIdentDomain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class GrandChild {

    @EmbeddedId
    private GrandChildId id;

    // GrandChildId.childId 매핑
    // 연관관계 주인
    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "CHILD_ID"),
            @JoinColumn(name = "PARENT_ID")
    })
    private Child child;

    private String name;

}
