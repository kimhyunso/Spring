package com.jpa.demo;

import com.jpa.demo.domain.Member;
import com.jpa.demo.domain.Order;
import jakarta.persistence.*;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        tx.commit();
    }


}
