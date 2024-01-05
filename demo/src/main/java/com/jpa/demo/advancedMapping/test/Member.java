package com.jpa.demo.advancedMapping.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member{
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue
    private Long id;

    private String username;

    @OneToOne
    @JoinTable(name = "MEMBER_LOCKER",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "LOCKER_ID")
    )
    private Locker locker;



//    public void setLocker(Locker locker){
//        this.locker = locker;
//        // this.locker.setMember(this);
//    }
}