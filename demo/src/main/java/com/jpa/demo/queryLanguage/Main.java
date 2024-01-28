package com.jpa.demo.queryLanguage;

import com.jpa.demo.domain.QOrderItem;
import com.jpa.demo.queryLanguage.domain1.Order;
import com.jpa.demo.queryLanguage.domain1.*;
import com.jpa.demo.queryLanguage.domain3.*;
import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.jpa.demo.queryLanguage.domain2.QMember.member;

public class Main {

    /**
     * em.find => 1차캐시에 있으면 갖고온다.
     *
     * JPQL 항상 데이터베이스에 SQL을 실행해서 결과를 먼저 조회 후, 1차 캐시에서 존재여부를 판단해 제거하거나 영속성 컨텍스트에 영속시킨다.
     * default => FlushModeType.AUTO
     * em.setFlushMode(FlushModeType.COMMIT);
     * 1. em.flush()
     * 2. JPQL => setFlushMode(FlushModeType.AUTO)
     *
     * 플러시 사용목적
     * 1. 방지 목적 (데이터 미스매치)
     * 2. 성능최적화
     * INSERT 1 -> flush / INSERT 1 -> flush / INSERT 1 -> flush
     * INSERT 3 -> flush [em.setFlushMode(FlushModeType.COMMIT)];
     */



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
            // jpqlProjectionQuery(em);
            init(em);
            // sataJoinTest(em);
            // joinOnTest(em);
            // fetchJoin(em);
            // init2(em);
//             insertData(em);
            /// dialectTest(em);
            // namedQueryTest(em);
            // criteriaSelect(em);
            // subQueryCriteria(em);
            // interactiveSubQueryCriteria(em);
            // inCriteria(em);
            // caseCriteria(em);
            // paramCriteria(em);
            // nativeCriteria(em);
            // queryDSL(em);
            // queryDSLPaging(em);
            beanCreateQueryDSL(em);
            tx.commit();
        }catch (Exception e){
            System.out.println("처리오류 : " + e.getMessage());
            tx.rollback();
        }
    }

    public static void queryDSL(EntityManager em){
        // JAPQuery
        JPAQueryFactory query = new JPAQueryFactory(em);

        /**
         * 별칭
         * QMember qMember = new QMember("m");
         * 기본 인스턴스 사용
         * QMember qMember = QMember.member;
         */


        List<com.jpa.demo.queryLanguage.domain2.Member> members =
                query
                .select(member)
                .from(member)
                .where(member.age.gt(25))
                .orderBy(member.name.desc()).fetch();

        members.stream().forEach(member -> {

            System.out.println("회원이름 : " + member.getName() + ", 회원나이 : " + member.getAge());
        });

    }

    public static void jpqlBasicQuery(EntityManager em){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QItem item = QItem.item;
        List<Item> items = query
                .selectFrom(item)
                // 상품 이름 : 좋은상품, 가격이 20000이상인거
//                .where(item.name.eq("좋은상품").and(item.price.gt(20000)))
                // 10000 ~ 20000 사이
//                .where(item.price.between(10000, 20000))
                // 상품이 포함되어있는지
//                .where(item.name.contains("상품1"))
                // 고급으로 시작하는지
                .where(item.name.startsWith("고급"))
                .offset(10).limit(10)
                .fetch();

        // fetch() => 여러개
        // fetchOne() => 단일건
    }

    public static void queryDSLPaging(EntityManager em){

        JPAQueryFactory query = new JPAQueryFactory(em);
        QItem item = QItem.item;

//        QueryModifiers queryModifiers = new QueryModifiers(2L, 1L); // limit, offset
//        List<Item> list = query
//                .selectFrom(item)
//                .restrict(queryModifiers)
//                .fetch();
//
//        list.stream().forEach(i -> {
//            System.out.println("상품이름 : " + i.getName() + ", 상품가격 : " + i.getPrice());
//        });

        List<Item> items = query
                .selectFrom(item)
                .groupBy(item.price)
                .having(item.price.gt(10000))
                .fetch();

        items.stream().forEach(i -> {
            System.out.println("상품이름 : " + i.getName() + ", 상품가격 : " + i.getPrice());
        });
    }
    public static void queryDSLJoin(EntityManager em){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QOrderItem orderItem = QOrderItem.orderItem;
//
//        List<Order> orders = query
//                .selectFrom(order)
//                .leftJoin(order.orderItems, orderItem)
//                .fetch();
//
//        List<Order> orders = query
//                .selectFrom(order)
//                .leftJoin(order.orderItems, orderItem)
//                .on(orderItem.count.gt(2))
//                .fetch();
        query.select(order)
                .from(order, member);
    }

    public static void subQueryQueryDSL(EntityManager em){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QItem item =  QItem.item;
        QItem itemSub = new QItem("itemSub");

        query.selectFrom(item)
                .where(item.price.eq(
                        JPAExpressions.select(itemSub.price)
                                .from(itemSub)
                ))
                .fetch();
    }

    public static void projectionQueryDSL(EntityManager em){
        QItem item = QItem.item;
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<String> result = query
                .select(item.name)
                .from(item)
                .fetch();

    }

    public static void beanCreateQueryDSL(EntityManager em){
        QItem item = QItem.item;
        JPAQueryFactory query = new JPAQueryFactory(em);
        // setter
        List<ItemDTO> result1 = query
                .select(Projections.bean(ItemDTO.class, item.name.as("username"), item.price))
                .from(item)
                .fetch();

        List<ItemDTO> result2 = query
                .select(Projections.fields(ItemDTO.class, item.name.as("username"), item.price))
                .from(item)
                .fetch();

        List<ItemDTO> result3 = query
                .select(Projections.constructor(ItemDTO.class, item.name.as("username"), item.price))
                .from(item)
                .fetch();

    }

    @QueryDelegate(Item.class)
    public static BooleanExpression isExpression(QItem item, Integer price){
        return item.price.gt(price);
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

    public static void nativeCriteria(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
        // TODO:  Cannot invoke "org.hibernate.type.Type.sqlTypes(org.hibernate.engine.spi.Mapping)" because "type" is null
        Root<com.jpa.demo.queryLanguage.domain2.Member> m = cq.from(com.jpa.demo.queryLanguage.domain2.Member.class);
        Expression<Integer> function = cb.function("SUM", Integer.class, m.get("age"));

        cq.select(function);

        em.createQuery(cq).getSingleResult();
        // System.out.println("회원 나이의 합계 : " + memberAgeSum);
    }

    public static void paramCriteria(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<com.jpa.demo.queryLanguage.domain2.Member> cq = cb.createQuery(com.jpa.demo.queryLanguage.domain2.Member.class);

        Root<com.jpa.demo.queryLanguage.domain2.Member> m = cq.from(com.jpa.demo.queryLanguage.domain2.Member.class);


        // 파라미터 바인딩을 사용한다. :: ? 사용
//        cq.select(m)
//                .where(cb.equal(m.get("name"), "MemberA"));

        cq.select(m)
                .where(cb.equal(m.get("name"), cb.parameter(String.class, "nameParam")));

        com.jpa.demo.queryLanguage.domain2.Member member = em.createQuery(cq)
                .setParameter("nameParam", "MemberC")
                .getSingleResult();

        System.out.println("회원이름 : " + member.getName() + ", 회원나이 : " + member.getAge());

    }

    public static void caseCriteria(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<com.jpa.demo.queryLanguage.domain2.Member> m = cq.from(com.jpa.demo.queryLanguage.domain2.Member.class);

        cq.multiselect(m.get("name"),
                cb.selectCase()
                        .when(cb.ge(m.<Integer>get("age"), 25), 600)
                        .when(cb.le(m.<Integer>get("age"), 28), 500)
                        .otherwise(1000));

        List<Object[]> members = em.createQuery(cq).getResultList();

        members.stream().forEach(rows -> {
            System.out.println("회원이름 : " + rows[0] + ", 회원나이 : " + rows[1]);
        });
    }

    public static void inCriteria(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<com.jpa.demo.queryLanguage.domain2.Member> cq = cb.createQuery(com.jpa.demo.queryLanguage.domain2.Member.class);

        Root<com.jpa.demo.queryLanguage.domain2.Member> m = cq.from(com.jpa.demo.queryLanguage.domain2.Member.class);

        cq.select(m)
                .where(cb.in(m.get("name"))
                        .value("MemberA")
                        .value("MemberB"));
        List<com.jpa.demo.queryLanguage.domain2.Member> members = em.createQuery(cq).getResultList();

        members.stream().forEach(member -> {
            System.out.println("회원이름 : " + member.getName());
        });
    }



    public static void interactiveSubQueryCriteria(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<com.jpa.demo.queryLanguage.domain2.Member> main = cb.createQuery(com.jpa.demo.queryLanguage.domain2.Member.class);
        Root<com.jpa.demo.queryLanguage.domain2.Member> m = main.from(com.jpa.demo.queryLanguage.domain2.Member.class);

        Subquery<com.jpa.demo.queryLanguage.domain2.Team> sub = main.subquery(com.jpa.demo.queryLanguage.domain2.Team.class);
        Root<com.jpa.demo.queryLanguage.domain2.Member> subM = sub.correlate(m);

        Join<com.jpa.demo.queryLanguage.domain2.Member, com.jpa.demo.queryLanguage.domain2.Team> t = subM.join("team");

        sub.select(t)
                .where(cb.equal(t.get("name"), "좋아요"));

        main.select(m)
                .where(cb.exists(sub));
        List<com.jpa.demo.queryLanguage.domain2.Member> members = em.createQuery(main).getResultList();

        members.stream().forEach(member -> {
            System.out.println("회원이름 : " + member.getName());
        });
    }

    public static void subQueryCriteria(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<com.jpa.demo.queryLanguage.domain2.Member> mainQuery = cb.createQuery(com.jpa.demo.queryLanguage.domain2.Member.class);
        Root<com.jpa.demo.queryLanguage.domain2.Member> m = mainQuery.from(com.jpa.demo.queryLanguage.domain2.Member.class);

        Subquery<Double> subQuery = mainQuery.subquery(Double.class);
        Root<com.jpa.demo.queryLanguage.domain2.Member> m2 = subQuery.from(com.jpa.demo.queryLanguage.domain2.Member.class);
        subQuery.select(cb.avg(m2.<Integer>get("age")));

        List<com.jpa.demo.queryLanguage.domain2.Member> members = em.createQuery(mainQuery.select(m)
                                                .where(cb.ge(m.<Integer>get("age"), subQuery)))
                                                            .getResultList();

        members.stream().forEach(member -> {
            System.out.println("회원이름 : " + member.getName() + ", 회원나이 : " + member.getAge());
        });

    }


    public static void criteriaSelect(EntityManager em){
        // Criteria 사용준비
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Member> query = builder.createQuery(Member.class);
//
//        // 루트 클래스 (조회를 시작할 클래스)
//        Root<Member> m = query.from(Member.class);
////
////        CriteriaQuery<Member> cq = query.select(m)
////                .where(builder.equal(m.get("age"), 30));
//
//        CriteriaQuery<Member> cq = query.select(m)
//                .where(builder.equal(m.get("age"), 30));
//
//        List<Member> members = em.createQuery(cq).getResultList();
//
//        for (Member member : members){
//            System.out.println("팀이름 : " + member.getTeam().getTeamName());
//            System.out.println("멤버이름 : " + member.getUsername());
//        }

        CriteriaBuilder cb = em.getCriteriaBuilder(); // 쿼리 빌더
//        CriteriaQuery<UserDTO> cq = cb.createQuery(UserDTO.class);
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);


//        Root<com.jpa.demo.queryLanguage.domain2.Member> m = cq.from(com.jpa.demo.queryLanguage.domain2.Member.class);
//
//        Join<com.jpa.demo.queryLanguage.domain2.Member, com.jpa.demo.queryLanguage.domain2.Team> t = m.join("team", JoinType.INNER);
//
//        List<Object[]> result = em.createQuery(cq.multiselect(m, t).where(cb.equal(t.get("name"), "좋아요")))
//                .getResultList();
//
//        result.stream().forEach(item -> {
//            System.out.println(((com.jpa.demo.queryLanguage.domain2.Member)item[0]).getName());
//            System.out.println(((com.jpa.demo.queryLanguage.domain2.Team)item[1]).getName());
//        });
//



//        Expression maxAge = cb.max(m.<Integer>get("age"));
//        Expression minAge = cb.min(m.<Integer>get("age"));
//
//
//        List<Object[]> result = em.createQuery(cq.multiselect(m.get("team").get("name"), maxAge, minAge)
//                .groupBy(m.get("team").get("name"))
//                        .having(cb.lt(minAge, 25))
//                        .orderBy(cb.desc(m.get("age"))))
//                .getResultList();
//
//        result.stream().forEach(item -> {
//            System.out.println("팀이름 : " + item[0]);
//            System.out.println("MaxAge : " + item[1]);
//            System.out.println("MinAge : " +item[2]);
//        });


//        List<Tuple> result = em.createQuery(cq.multiselect(
//                            m.alias("m"),
//                            m.get("name").alias("UserName"),
//                            m.get("age").alias("Age")))
//                                .getResultList();
//
//        result.stream().forEach(tuple -> {
//            com.jpa.demo.queryLanguage.domain2.Member member = tuple.get("m", com.jpa.demo.queryLanguage.domain2.Member.class);
//            String name = tuple.get("UserName", String.class);
//            Integer age = tuple.get("Age", Integer.class);
//
//            System.out.println("username : " + name + ", age : " + age + ", member : " + member);
//        });

        // String을 제외하고 타입을 명시해야함
//        Predicate lessAge = cb.gt(m.<Integer>get("age"), 10);
//
//        javax.persistence.criteria.Order ageDesc = cb.desc(m.get("age"));
//
//        List<com.jpa.demo.queryLanguage.domain2.Member> members = em.createQuery(
//                cq.select(m)
//                        .where(lessAge)
//                        .orderBy(ageDesc)
//        ).getResultList();
        
        
        // 조회 대상을 하나만 지정
        // cq.select(m);

        // 조회대상을 여러건
        // cq.multiselect(m.get("name"), m.get("age"));

        //
//        List<Object[]> members = em.createQuery(cq.select(cb.array(m.get("name"), m.get("age"))))
//                .getResultList();

//        List<Object[]> members = em.createQuery(cq.multiselect(m.get("name"), m.get("age")).distinct(true))
//                        .getResultList();
//
//        members.stream().forEach(member -> {
//            System.out.println("회원 이름 : " + member[0] + ", 회원나이 : " + member[1]);
//        });

        // JPQL new연산자
        // "select new [패키지명].dto"
//
//        List<UserDTO> result = em.createQuery(cq.select(cb.construct(UserDTO.class, m.get("name"), m.get("age"))))
//                .getResultList();
//
//        result.stream().forEach(member -> {
//            System.out.println("회원이름 : " + member.getUsername() + ", 회원나이 : " + member.getAge());
//        });

    }


    public static void queryDSLSelect(EntityManager em){
        // JPAQuery query = new JPAQuery(em);
        // QMember member = QMember.member;

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

        // Query로만 조회가능 TypeQuery로는 조회 x
        String sql = "SELECT o.members, o.product, o.orderAmount, o.address FROM Order o";

        List<Object[]> query = em.createQuery(sql).getResultList();

        for (Object[] row : query){
            Member member = (Member) row[0];
            Product product = (Product) row[1];
            int orderAmount = (Integer) row[2];
            Address address = (Address) row[3];

            System.out.println("주문자 : " + member.getUsername() + ", 나이 : " + member.getAge());
            System.out.println("주문상품 : " + product.getName() + ", 가격 : " + product.getPrice() + ", 구매갯수 : " + orderAmount);
            System.out.println("주소 : " + address.getCity() + address.getStreet() + address.getZipcode());

        }
    }

    public static void sataJoinTest(EntityManager em){
        String sql = "SELECT COUNT(m) FROM Member m, Team t "
                + "WHERE m.username = t.teamName";

         Integer count = em.createQuery(sql).getFirstResult();

        System.out.println(count);
    }


    // TODO: 확인하기
    public static void joinOnTest(EntityManager em){
//        String sql = "SELECT m, t FROM Member m "
//                + "LEFT JOIN m.team t "
//                + "ON t.name = '좋아요' ";
//
//        String sql = "SELECT t.members FROM Team t";
//
//        List<com.jpa.demo.queryLanguage.domain2.Member> resultList = em.createQuery(sql, com.jpa.demo.queryLanguage.domain2.Member.class).getResultList();
//        resultList.stream().forEach(member -> {
//            System.out.println("멤버이름 : " + member.getName());
//            System.out.println("멤버나이 : " + member.getAge());
//        });

//        String sql = "SELECT m.team FROM Member m";
//
//        List<com.jpa.demo.queryLanguage.domain2.Team> teams = em.createQuery(sql, com.jpa.demo.queryLanguage.domain2.Team.class).getResultList();
//
//        teams.stream().forEach(team -> {
//            System.out.println("팀이름 : " + team.getName());
//        });

        String sql = "SELECT t.members.size FROM Team t";

        Integer memberCount = em.createQuery(sql).getFirstResult();

        System.out.println(memberCount);


//        for (Object[] result : resultList){
            // com.jpa.demo.queryLanguage.domain2.Member member = (com.jpa.demo.queryLanguage.domain2.Member) result[0];
            // com.jpa.demo.queryLanguage.domain2.Team team = (com.jpa.demo.queryLanguage.domain2.Team) result[1];
            // System.out.println("팀이름 : " + team.getName());
            // System.out.println("이름 : " + member.getName() + ", 나이 " + member.getAge());
//        }
    }

    public static void dialectTest(EntityManager em){
//        String sql = "SELECT group_concat(i.name) FROM Item i";
//        List result = em.createQuery(sql).getResultList();
//        result.stream().forEach(i-> System.out.println(i));

        // SELECT m FROM Member m  => (m.id)

        com.jpa.demo.queryLanguage.domain2.Member member = new com.jpa.demo.queryLanguage.domain2.Member();
        member.setId(3L);

        String sql = "SELECT m FROM Member m WHERE m = :member";
        // sql = "SELECT m FROM Member m WHERE m.id = :memberId";

        List result = em.createQuery(sql)
                .setParameter("member", member)
                .getResultList();

        result.stream().forEach(item -> {
            com.jpa.demo.queryLanguage.domain2.Member m = (com.jpa.demo.queryLanguage.domain2.Member) item;
            System.out.println("이름 : " + m.getName() + ", 나이 : " + m.getAge());
        });


        com.jpa.demo.queryLanguage.domain2.Team team = em.find(com.jpa.demo.queryLanguage.domain2.Team.class, 1L);

        sql = "SELECT m FROM Member m WHERE m.team = :team";
        // sql = "SELECT m FROM Member m WHERE m.team.id = :teamId";

        result = em.createQuery(sql)
                .setParameter("team", team)
                .getResultList();

        result.stream().forEach(item -> {
            com.jpa.demo.queryLanguage.domain2.Member m = (com.jpa.demo.queryLanguage.domain2.Member) item;
            System.out.println("팀이름 : " + m.getTeam().getName() + ", 회원이름 : " + m.getName() + ", 회원나이 : " + m.getAge());
        });

    }

    public static void namedQueryTest(EntityManager em){
        List<com.jpa.demo.queryLanguage.domain2.Member> members = em.createNamedQuery("Member.findByUsername", com.jpa.demo.queryLanguage.domain2.Member.class)
                .setParameter("name", "MemberE")
                .getResultList();

        members.stream().forEach(member -> {
            System.out.println("회원이름 : " + member.getName() + ", 회원나이 : " + member.getAge());
        });

    }


    /**
     *  상태필드 경로 탐색
     *  SELECT m.username, m.age FROM Member m
     *
     *  (단일 값) 연관 경로 탐색 => 내부조인 :: 묵시적 내부조인만 일어남 (inner join)
     *  SELECT m.team FROM Member m
     *  암시적=> select * from member m join team t
     *  
     *  (컬렉션 값) 연관 경로 탐색 => 내부조인 :: 묵시적 내부조인
     *  SELECT t.members FROM Team t
     *
     *  SELECT m FROM Member m
     *  WHERE m.age > (select avg(m2.age) from Member m2)
     *
     *  SELECT m FROM Member m
     *  WHERE (select count(o) from Order o where m = o.member) > 0
     *  SELECT m FROM Member m
     *  WHERE m.orders.size > 0
     *
     *  SELECT m FROM Member m
     *  WHERE EXISTS (SELECT t FROM m.team t where t.name = '좋아요')
     *
     *  // 전체 상품 각각의 제고보다 주문량이 많은 주문들
     *  SELECT o FROM Order o
     *  WHERE o.orderAmount > ALL (select p.stockAmount from product p)
     *
     *  // 어떤 팀이든 소속된 회원
     *  SELECT m FROM Member m
     *  WHERE m.team = ANY (select t from Team t)
     *  
     *  // 20살 이상인 멤버가 소속되어 있는 팀
     *  SELECT t FROM Team t
     *  WHERE t IN (select t2 from Team t2 join t2.members m2 where m2.age >= 20)
     *
     *  // 10살 ~ 20살 멤버를 찾는다.
     *  SELECT m FROM Member m
     *  WHERE m.age BETWEEN 10 AND 20
     *
     *  // 컬렉션 식
     *  // order 주문을 안한 멤버
     *  SELECT m FROM Member m
     *  WHERE m.orders IS NOT EMPTY
     *  
     *  // 컬렉션 식
     *  // memberParam 파라미터가 팀안에 포함되어 있는지
     *  SELECT t FROM Team t
     *  WHERE :memberParam member of t.members
     *
     * 
     *  // CASE 식
     *  1. 기본 CASE
     *  2. 심플 CASE
     *  3. COALESCE
     *  4. NULLIF
     *
     *  1. 기본 CASE
     *  SELECT
     *      CASE
     *          WHEN m.age <= 10 THEN '학생요금'
     *          WHEN m.age >= 60 THEN '경로요금'
     *          ELSE '일반요금'
     *      END AS 요금
     *  FROM Member m
     *
     *  2. 심플 CASE
     *  SELECT
     *      CASE t.name
     *          WHEN '좋아요' THEN '인센티브110%'
     *          WHEN '싫어요' THEN '인센티브120%'
     *          ELSE '인센티브105%'
     *      END AS 월급인상률
     *  FROM Team t
     *
     *  3. COALESCE
     *  // m.username null이면, '이름 없는 회원'을 반환하라
     *  SELECT COALESCE(m.username, '이름 없는 회원') FROM Member m
     *
     *  4. NULLIF
     *  // 사용자 이름이 관리자이면 null을 반환하고 나머지는 본인의 이름을 반환하라
     *  SELECT NULLIF(m.username, '관리자') FROM Member m
     *
     *
     *
     * 
     */

    // 틀 -> 상속 -> 제품



    public static void insertData(EntityManager em){

        Album album = new Album();
        album.setName("앨범");
        album.setArtist("test");
        album.setEtc("to");
        album.setPrice(20000);
        album.setStockQuantity(10);
        em.persist(album);

        Book book = new Book();
        book.setAuthor("저자");
        book.setName("책");
        book.setPrice(10000);
        book.setIsbn("123-4356");
        book.setStockQuantity(5);
        em.persist(book);

        Movie movie = new Movie();
        movie.setName("영화");
        movie.setPrice(40000);
        movie.setActor("배우");
        movie.setDirector("감독");
        movie.setStockQuantity(10);
        em.persist(movie);
    }
    public static void fetchJoin(EntityManager em){
//        String sql = "SELECT t FROM Team t JOIN FETCH t.members WHERE t.name = '좋아요'";
//        List<com.jpa.demo.queryLanguage.domain2.Team> teams = em.createQuery(sql, com.jpa.demo.queryLanguage.domain2.Team.class).getResultList();
//        teams.stream().forEach(team -> {
//            System.out.println("팀이름 : " + team.getName());
//            // 페치조인으로 인해 지연로딩 발생 안함
//            for (com.jpa.demo.queryLanguage.domain2.Member member : team.getMembers()){
//                System.out.println("멤버이름 : " + member.getName() + ", member : " + member);
//            }
//        });

        String sql = "SELECT m FROM Member m join fetch m.team ORDER BY m.team.name DESC";

        List<com.jpa.demo.queryLanguage.domain2.Member> members = em.createQuery(sql, com.jpa.demo.queryLanguage.domain2.Member.class).getResultList();

        members.stream().forEach(member -> {
            System.out.println("팀이름 : " + member.getTeam().getName());
            System.out.println("이름 : " + member.getName() + ", 나이 : " + member.getAge());
        });

    }

    private static void init2(EntityManager em){
        com.jpa.demo.queryLanguage.domain2.Team teamHate = com.jpa.demo.queryLanguage.domain2.Team.builder()
                .name("싫어요")
                .build();

        com.jpa.demo.queryLanguage.domain2.Team teamLike = com.jpa.demo.queryLanguage.domain2.Team.builder()
                .name("좋아요")
                .build();

        com.jpa.demo.queryLanguage.domain2.Member memberA = com.jpa.demo.queryLanguage.domain2.Member.builder()
                .team(teamHate)
                .age(30)
                .name("MemberA")
                .build();

        com.jpa.demo.queryLanguage.domain2.Member memberB = com.jpa.demo.queryLanguage.domain2.Member.builder()
                .team(teamLike)
                .age(25)
                .name("MemberB")
                .build();


        com.jpa.demo.queryLanguage.domain2.Member memberC = com.jpa.demo.queryLanguage.domain2.Member.builder()
                .team(teamHate)
                .age(23)
                .name("MemberC")
                .build();

        com.jpa.demo.queryLanguage.domain2.Member memberD = com.jpa.demo.queryLanguage.domain2.Member.builder()
                .team(teamLike)
                .age(27)
                .name("MemberD")
                .build();

        com.jpa.demo.queryLanguage.domain2.Member memberE = com.jpa.demo.queryLanguage.domain2.Member.builder()
                .team(teamHate)
                .age(25)
                .name("MemberE")
                .build();

        em.persist(teamHate);
        em.persist(teamLike);

        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);
        em.persist(memberE);
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

        Order order5 = Order.builder()
                .orderAmount(5)
                .address(address1)
                .product(productA)
                .member(memberA)
                .build();

        Order order10 = Order.builder()
                .orderAmount(10)
                .address(address2)
                .product(productA)
                .member(memberB)
                .build();

        Order order7 = Order.builder()
                .orderAmount(7)
                .member(memberC)
                .address(address3)
                .product(productA)
                .build();

        em.persist(productA);

        em.persist(order7);
        em.persist(order5);
        em.persist(order10);




        em.persist(teamLike);
        em.persist(teamHate);

        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);
        em.persist(memberE);
    }

}
