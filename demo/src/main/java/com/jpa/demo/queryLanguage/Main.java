package com.jpa.demo.queryLanguage;

import com.jpa.demo.queryLanguage.domain1.Member;
import com.jpa.demo.queryLanguage.domain1.Team;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            // jpqlSelect(em);
            criteriaSelect(em);
            tx.commit();
        }catch (Exception e){
            System.out.println("처리오류 : " + e.getMessage());
            tx.rollback();
        }
    }


    public static void jpqlSelect(EntityManager em){
        // init(em);

        String jpql = "select m from Member as m where m.age >= 30";
        List<Member> members = em.createQuery(jpql, Member.class).getResultList();

        for (Member member : members){
            System.out.println("팀이름 : " + member.getTeam().getTeamName());
            System.out.println("멤버이름 : " + member.getUsername());
        }
    }


    public static void criteriaSelect(EntityManager em){
        // Criteria 사용준비
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = builder.createQuery(Member.class);

        // 루트 클래스 (조회를 시작할 클래스)
        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m)
                .where(builder.equal(m.get("age"), 30));
        List<Member> members = em.createQuery(cq).getResultList();

        for (Member member : members){
            System.out.println("팀이름 : " + member.getTeam().getTeamName());
            System.out.println("멤버이름 : " + member.getUsername());
        }

    }

    private static void init(EntityManager em){
        Team teamHate = Team.builder()
                .teamName("싫어요")
                .build();

        Team teamLike = Team.builder()
                .teamName("좋아요")
                .build();


        Member memberA = Member.builder()
                .age(30)
                .username("kim")
                .team(teamLike)
                .build();

        Member memberB = Member.builder()
                .age(25)
                .username("kim")
                .team(teamLike)
                .build();

        Member memberC = Member.builder()
                .age(21)
                .username("lee")
                .team(teamHate)
                .build();

        Member memberD = Member.builder()
                .age(50)
                .username("park")
                .team(teamHate)
                .build();

        Member memberE = Member.builder()
                .age(30)
                .username("hong")
                .team(teamLike)
                .build();
        em.persist(teamLike);
        em.persist(teamHate);

        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);
        em.persist(memberE);


    }

}
