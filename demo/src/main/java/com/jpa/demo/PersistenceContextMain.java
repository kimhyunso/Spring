package com.jpa.demo;

import com.jpa.demo.domainTest.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PersistenceContextMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember("memberA", "회원1");

        member.setName("회원명변경");
        mergeMember(member);
    }


    private static Member createMember(String id, String name){
        Member member = Member.builder()
                .id(id)
                .age(20)
                .name(name)
                .build();

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(member);
        tx.commit();

        em.close();

        return member;

    }

    private static void mergeMember(Member member){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        // 준영속, 비영속 상태를 신경 쓰지 않고 전부 영속상태로 만든다. save or update 기능 수행
        Member mergeMember = em.merge(member);
        tx.commit();

        // 준영속상태
        System.out.println("Member : " + member.getId() + " Age : " + member.getAge() + " Name : " + member.getName());


        // 영속상태
        System.out.println("mergeMember : " + mergeMember.getId() + " Age : " + mergeMember.getAge() + " Name : " + mergeMember.getName());

        System.out.println("em contains member : " + em.contains(member));
        System.out.println("em contains mergeMember : " + em.contains(mergeMember));

        em.close();
    }

    private static void testDetached(EntityManager em){
        Member member = Member.builder()
                .id("memberA")
                .name("회원A")
                .build();

        // 회원 엔티티 영속상태
        em.persist(member);

        // 회원 엔티티를 영속성 컨텍스트에서 분리
        em.detach(member); // 준영속상태
        // 데이터베이스에 반영 안됨
    }
}
