package com.jpa.demo.queryLanguage;

import com.jpa.demo.queryLanguage.domain1.Member;
import com.jpa.demo.queryLanguage.domain1.QMember;
import com.jpa.demo.queryLanguage.domain1.Team;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            jpqlSelect(em);
            // criteriaSelect(em);
            // nativeSQLSelect(em);
            tx.commit();
        }catch (Exception e){
            System.out.println("처리오류 : " + e.getMessage());
            tx.rollback();
        }
    }


    public static void jpqlSelect(EntityManager em) {
        // init(em);

        // 1. 대소문자 구분함
        // 2. 별칭(식별변수)은 무조건 줘야한다.

        /**
         *  반환할 타입이 명확 => TypeQuery
         *  반환 타입이 불명확 => Query
         */

//        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.username = 'kim'", Member.class);
//
//        List<Member> resultList = query.getResultList();
//        for (Member member : resultList){
//            System.out.println("팀이름 : " + member.getTeam().getTeamName());
//            System.out.println("멤버이름 : " + member.getUsername());
//        }
//
//        Query query1 = em.createQuery("SELECT m.username, m.age FROM Member m");
//        List<Member> resultList1 = query1.getResultList();
//        for (Member member : resultList){
//            System.out.println("팀이름 : " + member.getTeam().getTeamName());
//            System.out.println("멤버이름 : " + member.getUsername());
//        }
//
//        String jpql = "select m from Member m where m.age >= 30";
//        List<Member> members = em.createQuery(jpql, Member.class).getResultList();
//
//        for (Member member : members){
//            System.out.println("팀이름 : " + member.getTeam().getTeamName());
//            System.out.println("멤버이름 : " + member.getUsername());
//        }
        // 파라미터 바인딩
//        String usernameParam = "kim";
//
//        TypedQuery<Member> query =
//                em.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class);
//        query.setParameter("username", usernameParam);
//
//        List<Member> members = query.getResultList();
        // 메소드 체인 방식
//        String usernameParam = "kim";
//        List<Member> members =
//                em.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class)
//                        .setParameter("username", usernameParam)
//                        .getResultList();

        // 위치 기준 파라미터
        String usernameParam = "park";
        List<Member> members = em.createQuery("SELECT m FROM Member m WHERE m.username = ?1", Member.class)
                .setParameter(1, usernameParam)
                .getResultList();

        for (Member member : members) {
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
//
//        CriteriaQuery<Member> cq = query.select(m)
//                .where(builder.equal(m.get("age"), 30));

        CriteriaQuery<Member> cq = query.select(m)
                .where(builder.equal(m.get("age"), 30));

        List<Member> members = em.createQuery(cq).getResultList();

        for (Member member : members){
            System.out.println("팀이름 : " + member.getTeam().getTeamName());
            System.out.println("멤버이름 : " + member.getUsername());
        }
    }


    public static void queryDSLSelect(EntityManager em){
        JPAQuery query = new JPAQuery(em);
        QMember member = QMember.member;

    }


    public static void nativeSQLSelect(EntityManager em){
        String sql = "SELECT * FROM MEMBER WHERE NAME = 'kim'";

        List<Member> members = em.createNativeQuery(sql, Member.class).getResultList();

        for (Member member : members){
            System.out.println("팀이름" + member.getTeam().getTeamName());
            System.out.println("유저이름" + member.getUsername());
        }
    }

    public static void getSession(EntityManager em){
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                // work.. 우회하는 방법
            }
        });

    }


    /**
     *  프로젝션 : SELECT 절에 조회할 대상을 지정하는 것
     *  1. 엔티티
     *  2. 임베디드
     *  3. 스칼라 타입 (문자, 숫자..)
     *  # 엔티티 프로젝션
     *  SELECT m FROM Member m
     *  SELECT m.team FROM Member m
     *  --- 영속성 컨텍스에서 관리 ---
     *
     *  # 임베디드 프로젝션
     *  SELECT a FROM Address a (x)
     *  SELECT m.Address FROM Member m (o)
     *  --- 영속성 컨텍스트에서 관리 x ---
     *  
     *  # 스칼라 타입
     *  SELECT m.username, m.age FROM Member
     *
     */

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
