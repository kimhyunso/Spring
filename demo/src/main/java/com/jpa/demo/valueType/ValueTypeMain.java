package com.jpa.demo.valueType;

import com.jpa.demo.valueType.domain2.Address;
import com.jpa.demo.valueType.domain2.Member;
import com.jpa.demo.valueType.domain2.Period;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Date;

public class ValueTypeMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            embeddedSave(em);

            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            tx.rollback();
        }
    }

    public static void embeddedSave(EntityManager em){
        Address address = Address.builder()
                .city("seoul")
                .street("test")
                .zipcode("171-171")
                .build();

        Member member = new Member();
        member.setName("멤버1");
        member.setHomeAddress(address);
        member.setWorkPeriod(new Period());

        em.persist(member);
    }

}
