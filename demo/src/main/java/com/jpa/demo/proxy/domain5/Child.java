package com.jpa.demo.proxy.domain5;

import com.jpa.demo.proxy.domain4.Parent;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Child {

    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    @ManyToOne
    private Parent parent;
}
