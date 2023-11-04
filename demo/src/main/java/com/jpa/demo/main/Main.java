package com.jpa.demo.main;


import com.jpa.demo.domain.Member;
import jakarta.persistence.*;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




public class Main {

    public static void main(String[] args) {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            logic(em);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

    private static void logic(EntityManager em){
        String id = "id1";
        Member member = Member.builder()
                .id(id)
                .age(2)
                .name("지한")
                .build();

        // 저장
        em.persist(member);
    
        // 수정
        member.setAge(20);
        
        // 단일조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember = " + findMember.getName() + " age = " + findMember.getAge());
        
        // 다수조회
        List<Member> members =
                em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("member.size = " + members.size());
        
        // 삭제
        em.remove(members);

    }


}
