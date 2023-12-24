package com.jpa.demo.proxy;

import com.jpa.demo.proxy.Domain1.Member;
import com.jpa.demo.proxy.Domain1.Team;
import jakarta.persistence.*;

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

        // proxyMemberFind(findMemberId, em);
        Long findTeam = 1L;
        // proxyTeamFind(findTeam, em);

        // poxyFinds(findMemberId, findTeam, em);
        // eagerLoadingSaveTest(em);
        // eagerLoadingFindTest(findMemberId, em);
        lazyLoadingFindTest(findMemberId, em);
        tx.commit();
        em.close();

        // ERROR
        // System.out.println("준영속 상태 초기화 시도 : " + member.getUsername());
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

    public static void proxyMemberFind(Long memberId, EntityManager em){
        // 지연로딩됨
        Member member = em.getReference(Member.class, memberId);
        // 실제 사용을 할 때, select함 :: 프록시 객체의 초기화
        // 1. getName();
        System.out.println("회원이름 : " + member.getUsername());
    }

    public static void proxyTeamFind(Long teamId, EntityManager em){
        Team team = em.getReference(Team.class, teamId);

        // 1. SELECT를 하지 않음 : 영속성 컨텍스트가 DB에 접근하지 않음
        // 2. 프록시 객체가 초기화 되지 않음
        System.out.println("팀 PK : " + team.getId());
        // System.out.println("팀이름 : " + team.getName());
    }

    public static void poxyFinds(Long memberId, Long teamId, EntityManager em){
        Member member = em.find(Member.class, memberId);
        Team team = em.getReference(Team.class, teamId);
        // Team team = em.find(Team.class, teamId);
        // 사용을 하게됨 => ProxyEntity 초기화
        member.setTeam(team);

        /**
         * find로 select, update
         * 1. member find => select 1
         * 2. team find => select 2
         * 3. setTeam => update 1
         */

        /**
         * getReference로 select, update
         * 1. member find => select 1
         * 2. setTeam => update 1
         */

        // proxy 초기화 여부 확인
        boolean isLoad = em.getEntityManagerFactory()
                .getPersistenceUnitUtil().isLoaded(team);

        // boolean isLoad = emf.getPersistenceUnitUtil().isLoaded(entity);

        System.out.println("isLoad : " + isLoad);
        System.out.println("teamProxy = " + team.getClass().getName());
    }
    
    public static void test(EntityManager em){
        /**
         *  1. member + team 같이 select 하는 것이 좋을까 :: 즉시로딩
         *  2. member select => team 사용시점에 select를 하는 것이 좋을까 :: 지연로딩
         */

        Member member = em.find(Member.class, 1L);
        // 객체그래프탐색
        Team team = member.getTeam();
        System.out.println("팀이름 : " + team.getName());

    }

    public static void eagerLoadingSaveTest(EntityManager em){

        com.jpa.demo.proxy.Domain2.Team teamLove = com.jpa.demo.proxy.Domain2.Team.builder()
                .name("좋아요팀")
                .build();


        com.jpa.demo.proxy.Domain2.Member memberLoveA = com.jpa.demo.proxy.Domain2.Member.builder()
                .username("좋아요멤버A")
                .team(teamLove)
                .build();


        com.jpa.demo.proxy.Domain2.Member memberLoveB = com.jpa.demo.proxy.Domain2.Member.builder()
                .username("좋아요멤버B")
                .team(teamLove)
                .build();

        com.jpa.demo.proxy.Domain2.Team teamHate = com.jpa.demo.proxy.Domain2.Team.builder()
                .name("싫어요팀")
                .build();

        com.jpa.demo.proxy.Domain2.Member memberHateA = com.jpa.demo.proxy.Domain2.Member.builder()
                .team(teamHate)
                .username("싫어요멤버A")
                .build();

        com.jpa.demo.proxy.Domain2.Member memberHateB = com.jpa.demo.proxy.Domain2.Member.builder()
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

    // 1. 즉시로딩
    public static void eagerLoadingFindTest(Long findMemberId, EntityManager em){

        /**
         *  즉시 로딩
         *  select join ==> 이미 Team에 대한 데이터가 들어와 있다는 것
         */
        /**
         *      select
         *         m1_0.MEMBER_ID,
         *         t1_0.TEAM_ID,
         *         t1_0.name,
         *         m1_0.username
         *     from
         *         Member m1_0
         *     left join
         *         Team t1_0
         *             on t1_0.TEAM_ID=m1_0.TEAM_ID
         *     where
         *         m1_0.MEMBER_ID=?
         */
        com.jpa.demo.proxy.Domain2.Member member = em.find(com.jpa.demo.proxy.Domain2.Member.class, findMemberId);
        com.jpa.demo.proxy.Domain2.Team team = member.getTeam();
    }
    
    // 2. 지연로딩
    public static void lazyLoadingFindTest(Long findMemberId, EntityManager em){

        /**
         *  지연로딩 : Team Entity를 쓰기 전까지 select해오지 않음 :: 초기화 x
         *
         *     select
         *         m1_0.MEMBER_ID,
         *         m1_0.TEAM_ID,
         *         m1_0.username
         *     from
         *         Member m1_0
         *     where
         *         m1_0.MEMBER_ID=?
         */

        com.jpa.demo.proxy.Domain2.Member member = em.find(com.jpa.demo.proxy.Domain2.Member.class, findMemberId);
        com.jpa.demo.proxy.Domain2.Team team = member.getTeam();
        // ProxyEntity 초기화
        /**
         *     select
         *         t1_0.TEAM_ID,
         *         t1_0.name
         *     from
         *         Team t1_0
         *     where
         *         t1_0.TEAM_ID=?
         */
        System.out.println(team.getName());
    }






}
