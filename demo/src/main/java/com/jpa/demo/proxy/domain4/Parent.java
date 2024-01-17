package com.jpa.demo.proxy.domain4;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Parent {
    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Child> children = new ArrayList<>();
}
