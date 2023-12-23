package com.jpa.demo.proxy;

import com.jpa.demo.proxy.Domain1.Member;
import com.jpa.demo.proxy.Domain1.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProxyMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");



    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // proxySaveTest(em);

        Long findMemberId = 1L;

        // printUserAndTeam(findMemberId, em);

        // printUser(findMemberId, em);

        proxyFind(findMemberId, em);
        tx.commit();
    }


    public static void proxySaveTest(EntityManager em){
        Team teamLove = Team.builder()
                .name("좋아요팀")
                .build();


        Member memberLoveA = Member.builder()
                .username("좋아요멤버A")
                .team(teamLove)
                .build();


        Member memberLoveB = Member.builder()
                .username("좋아요멤버B")
                .team(teamLove)
                .build();

        Team teamHate = Team.builder()
                .name("싫어요팀")
                .build();

        Member memberHateA = Member.builder()
                .team(teamHate)
                .username("싫어요멤버A")
                .build();

        Member memberHateB = Member.builder()
                .team(teamHate)
                .username("싫어요멤버B")
                .build();


        em.persist(teamLove);
        em.persist(teamHate);
        em.persist(memberLoveA);
        em.persist(memberLoveB);
        em.persist(memberHateA);
        em.persist(memberHateB);
    }


    public static void printUserAndTeam(Long memberId, EntityManager em){
        Member member = em.find(Member.class, memberId);
        // 팀정보를 갖고옴
        Team team = member.getTeam();

        System.out.println("회원이름 : " + member.getUsername());
        System.out.println("소속팀 : " + team.getName());
    }

    public static void printUser(Long memberId, EntityManager em){

        // Member를 조회할 때, Team까지 조회하는 것은 효율적이지 않음

        Member member = em.find(Member.class, memberId);
        /**
         *  실제 엔티티가 사용될때, (member.getTeam() 같은 경우)
         *  그 엔티티를 조회하는 경우 (team select) ==> 지연로딩
         *
         * */
        // member.getTeam();

        System.out.println("회원이름 : " + member.getUsername());
    }

    public static void proxyFind(Long memberId, EntityManager em){
        // 지연로딩됨
        Member member = em.getReference(Member.class, memberId);
        // 실제 사용을 할 때, select함 :: 프록시 객체의 초기화
        // 1. getName();
        System.out.println("회원이름 : " + member.getUsername());
    }

}
