package com.example.chapter14.domain;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Team {
    @Id
    private String id;
    
    // 하이버네이트에서 준비한 컬레션으로 감싸서 사용
    @OneToMany
    @JoinColumn
    private Collection<Member> members = new ArrayList<Member>();

    // org.hibernate.collection.internal.PersistentBag
    @OneToMany
    Collection<Member> collection = new ArrayList<>();

    // org.hibernate.collection.internal.PersistentBag
    @OneToMany
    List<Member> list = new ArrayList<>();

    // org.hibernate.collection.internal.PersistentSet
    @OneToMany(mappedBy = "team")
    @OrderBy("username desc, id asc") // Set에 OrderBy 사용시, LinkedHashSet을 내부에서 사용함
    Set<Member> set = new HashSet<>();

    // org.hibernate.collection.internal.PersistentList
    @OneToMany
    @OrderColumn
    List<Member> orderColumnList = new ArrayList<>();




}
