package com.jpa.demo.relationMapping;

import com.jpa.demo.relationMapping.domain.Member;
import com.jpa.demo.relationMapping.domain.Team;
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

        testSave(em);

        tx.commit();
    }



    public static void testSave(EntityManager em){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        Team team1 = new Team("team1");



        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(team1);
        // insert member1
        em.persist(member1);
        // insert member2
        em.persist(member2);
        // insert team1, update member1.fk, update member2.fk

    }
}
