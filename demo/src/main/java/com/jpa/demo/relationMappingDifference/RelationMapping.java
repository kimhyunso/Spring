package com.jpa.demo.relationMappingDifference;

import com.jpa.demo.relationMappingDifference.domain.Member;
import com.jpa.demo.relationMappingDifference.domain.Team;
import com.jpa.demo.relationMappingDifference.manyToMany.domain.Product;
import com.jpa.demo.relationMappingDifference.oneToOne.domain.Locker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class RelationMapping {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // testSave(em);

        // oneToOneTestSave(em);

        // manyToManyTestSave(em);
        manyToManyTestFind(em);

        tx.commit();
    }

    // 단방향 insert -> update
    // 양방향 insert

    public static void testSave(EntityManager em){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");


        Team team1 = new Team("team1");

        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        // insert member1
        em.persist(member1);
        // insert member2
        em.persist(member2);
        // insert team1, update member1.fk, update member2.fk
        em.persist(team1);
    }



    public static void oneToOneTestSave(EntityManager em){

        // 1. 주 테이블이 연관관계의 주인인 경우
//        Locker locker = Locker.builder()
//                .name("사물함2")
//                .build();
//
//
//        com.jpa.demo.relationMappingDifference.oneToOne.domain.Member member = com.jpa.demo.relationMappingDifference.oneToOne.domain.Member.builder()
//                .username("MEMBER2")
//                .locker(locker)
//                .build();

        // insert locker
        // em.persist(locker);
        // insert member
        // em.persist(member);


        // 2. 주 테이블이 아닌 상대테이블이 연관관계 주인인 경우


        com.jpa.demo.relationMappingDifference.oneToOne.domain.Member member = com.jpa.demo.relationMappingDifference.oneToOne.domain.Member.builder()
                .username("MEMBER3")
                .build();

        Locker locker = Locker.builder()
                .member(member)
                .name("사물함2")
                .build();
        // insert locker
        em.persist(locker);
        // insert member, update locker
        em.persist(member);
    }

    public static void manyToManyTestSave(EntityManager em){

        // 1. 다대다 단방향

        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");

        em.persist(productA);


        com.jpa.demo.relationMappingDifference.manyToMany.domain.Member member = new com.jpa.demo.relationMappingDifference.manyToMany.domain.Member();

        member.setId("member1");
        member.setUsername("회원1");
        member.getProducts().add(productA);

        em.persist(member);
    }

    public static void manyToManyTestFind(EntityManager em){
        com.jpa.demo.relationMappingDifference.manyToMany.domain.Member member = em.find(com.jpa.demo.relationMappingDifference.manyToMany.domain.Member.class, "member1");

        List<Product> products = member.getProducts();

        for (Product product : products) {
            System.out.println("product.name = " + product.getName());
        }

    }





}
