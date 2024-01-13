package com.jpa.demo.queryLanguage;

import com.jpa.demo.queryLanguage.domain1.*;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            // jpqlSelect(em);
            // criteriaSelect(em);
            // nativeSQLSelect(em);
            // testProjection(em);
            // newTest(em);
            // jpqlJoinQuery(em);
            // jpqlOuterQuery(em);
            // jpqlCollectionJoin(em);
            jpqlProjectionQuery(em);
            // init(em);
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

    public static void testProjection(EntityManager em){
        List<Object[]> listResult = em.createQuery("SELECT m.username, m.age FROM Member m").getResultList();

        Iterator iterator = listResult.iterator();

        List<UserDTO> userDTOList = new ArrayList<>();

        while (iterator.hasNext()){
            Object[] row = (Object[]) iterator.next();
            UserDTO userDTO = new UserDTO((String)row[0], (Integer) row[1]);
            String username = (String)row[0];
            Integer age = (Integer) row[1];
            userDTOList.add(userDTO);
            System.out.println("이름 : " + username + " 나이 : " + age);
        }
    }
    // TODO: 확인하기
    /**
     * QueryType => 엔티티 타입으로 조회할 때
     * Query => 스칼라, 임베디드, 엔티티 타입도 조회할 때 사용
     *
     * ex) SELECT o.member, o.product, o.orderAmount FROM Order o
     *
     */


    public static void newTest(EntityManager em){

        // 1. 패키지 명을 포함한 클래스 명을 작성
        // 2. 생성자가 있어야함


        // FirstResult : 조회 시작 위치 11 ~ 30 (20건의 데이터 조회)
        // MaxResults : (20건 데이터를 조회)
        List<UserDTO> userDTOList = em.createQuery("SELECT new com.jpa.demo.queryLanguage.domain1.UserDTO(m.username, m.age) FROM Member m", UserDTO.class)
                .setFirstResult(10)
                .setMaxResults(20)
                .getResultList();

        for (UserDTO dto : userDTOList){
            System.out.println("이름 : " + dto.getUsername());
            System.out.println("나이 : " + dto.getAge());
        }

    }

    public static void jpqlJoinQuery(EntityManager em){
        String teamName = "좋아요";
        String query = "SELECT new com.jpa.demo.queryLanguage.domain1.UserDTO(m.username, m.age) "
                + "FROM Member m "
                + "INNER JOIN m.team t "
                + "WHERE t.teamName = :teamName "
                + "ORDER BY m.age DESC";

        List<UserDTO> members = em.createQuery(query, UserDTO.class)
                .setParameter("teamName", teamName)
                .getResultList();

        for (UserDTO member : members){
            System.out.println("이름 : " + member.getUsername());
            System.out.println("나이 : " + member.getAge());
        }
    }

    public static void jpqlOuterQuery(EntityManager em){
        String teamName = "좋아요";
        String query = "SELECT new com.jpa.demo.queryLanguage.domain1.UserDTO(m.username, m.age) "
                + "FROM Member m "
                + "LEFT OUTER JOIN m.team t "
                + "WHERE t.teamName = :teamName "
                + "ORDER BY m.age DESC";
        // Query
        List<UserDTO> members = em.createQuery(query, UserDTO.class)
                .setParameter("teamName", teamName)
                .getResultList();

        for (UserDTO member : members){
            System.out.println("이름 : " + member.getUsername());
            System.out.println("나이 : " + member.getAge());
        }
    }

    public static void jpqlCollectionJoin(EntityManager em){
        String sql = "SELECT t "
                + "FROM Team t "
                + "LEFT JOIN t.members m";

        // QueryType
        List<Team> teams = em.createQuery(sql, Team.class)
                .getResultList();

        teams.stream().forEach(team -> {
            for (Member member : team.getMembers()){
                System.out.println("팀이름 : " + team.getTeamName() + ", 멤버이름 : " + member.getUsername() + ", 멤버나이 : " + member.getAge());
            }
        });

//        List<Object[]> teams = em.createQuery(sql)
//                .getResultList();
//
//        Iterator iterator = teams.iterator();
//
//        while (iterator.hasNext()){
//            Object o = iterator.next();
//            Team team = (Team) o;
//            System.out.println("팀이름 : " + team.getTeamName());
//            for (Member member : team.getMembers()){
//                System.out.println("멤버이름 : " + member.getUsername() + ", 멤버나이 : " + member.getAge());
//            }
//        }
    }


    public static void jpqlProjectionQuery(EntityManager em){
        String sql = "SELECT o.members, o.product, o.orderAmount FROM Order o";

        List<Object[]> query = em.createQuery(sql).getResultList();

        for (Object[] row : query){
            Member member = (Member) row[0];
            Product product = (Product) row[1];
            int orderAmount = (Integer) row[2];

            System.out.println("주문자 : " + member.getUsername() + ", 나이 : " + member.getAge());
            System.out.println("주문상품 : " + product.getName() + ", 가격 : " + product.getPrice() + ", 구매갯수 : " + orderAmount);
        }
    }



    private static void init(EntityManager em){
        Team teamHate = Team.builder()
                .teamName("싫어요")
                .build();

        Team teamLike = Team.builder()
                .teamName("좋아요")
                .build();

        Address address1 = new Address("강남", "언주로", "123-123");
        Address address2 = new Address("경기도", "남양주", "1334-123");

        Address address3 = new Address("부산", "해운대구", "678-123");


        Product productA = Product.builder()
                .name("상품A")
                .price(20000)
                .stockAmount(100)
                .build();

        Order order5 = Order.builder()
                .orderAmount(5)
                .address(address1)
                .product(productA)
                .build();

        Order order10 = Order.builder()
                .orderAmount(10)
                .address(address2)
                .product(productA)
                .build();

        Order order7 = Order.builder()
                .orderAmount(7)
                .address(address3)
                .product(productA)
                .build();

        em.persist(productA);

        em.persist(order7);
        em.persist(order5);
        em.persist(order10);

        Member memberA = Member.builder()
                .age(30)
                .username("kim")
                .team(teamLike)
                .order(order5)
                .build();

        Member memberB = Member.builder()
                .age(25)
                .username("kim")
                .team(teamLike)
                .order(order10)
                .build();

        Member memberC = Member.builder()
                .age(21)
                .username("lee")
                .team(teamHate)
                .order(order7)
                .build();

        Member memberD = Member.builder()
                .age(50)
                .username("park")
                .team(teamHate)
                .order(order10)
                .build();

        Member memberE = Member.builder()
                .age(30)
                .username("hong")
                .team(teamLike)
                .order(order7)
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
