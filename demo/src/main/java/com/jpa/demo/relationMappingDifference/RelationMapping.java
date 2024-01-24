package com.jpa.demo.relationMappingDifference;

import com.jpa.demo.relationMappingDifference.domain.Member;
import com.jpa.demo.relationMappingDifference.domain.Team;
import com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDBVersionDomain.Order;
import com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain.MemberProduct;
import com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain.MemberProductId;
import com.jpa.demo.relationMappingDifference.manyToMany.domain.Product;
import com.jpa.demo.relationMappingDifference.oneToOne.domain.Locker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
// 1. 식별관계 : 매핑테이블 기본키 + 외레키 => 복합키
// 2. 비식별관계 : 매핑테이블 기본키 => 대리키 :: 추천

public class RelationMapping {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

//    public static void main(String[] args) {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        // testSave(em);
//
//        // oneToOneTestSave(em);
//
//        // manyToManyTestSave(em);
//        // manyToManyTestFind(em);
//        // findInverse(em);
//        // InteractiveSaveTest(em);
//        // interactiveFindTest(em);
//        // complexDBVersionSaveTest(em);
//        // complexDBVersionFindTest(em);
//        다대다단뱡향Test(em);
//
//        tx.commit();
//    }


    public static void 다대다단뱡향Test(EntityManager em){
        com.jpa.demo.relationMappingDifference.manyToMany.domain.Member member = new com.jpa.demo.relationMappingDifference.manyToMany.domain.Member();
        member.setId("member1");
        member.setUsername("회원A");


        Product product = new Product();
        product.setId("product1");
        product.setName("상품A");

        List<Product> products = new ArrayList<>();
        products.add(product);

        member.setProducts(products);

        em.persist(member);
        em.persist(product);
    }


    // 단방향 insert -> update
    // 양방향 insert

    public static void testSave(EntityManager em){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");


        Team team1 = new Team("team1");

        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        // insert member1
        em.persist(member1);
        // insert member2
        em.persist(member2);
        // insert team1, update member1.fk, update member2.fk
        em.persist(team1);
    }



    public static void oneToOneTestSave(EntityManager em){

        // 1. 주 테이블이 연관관계의 주인인 경우
//        Locker locker = Locker.builder()
//                .name("사물함2")
//                .build();
//
//
//        com.jpa.demo.relationMappingDifference.oneToOne.domain.Member member = com.jpa.demo.relationMappingDifference.oneToOne.domain.Member.builder()
//                .username("MEMBER2")
//                .locker(locker)
//                .build();

        // insert locker
        // em.persist(locker);
        // insert member
        // em.persist(member);


        // 2. 주 테이블이 아닌 상대테이블이 연관관계 주인인 경우


        com.jpa.demo.relationMappingDifference.oneToOne.domain.Member member = com.jpa.demo.relationMappingDifference.oneToOne.domain.Member.builder()
                .username("MEMBER3")
                .build();

        Locker locker = Locker.builder()
                .member(member)
                .name("사물함2")
                .build();
        // insert locker
        em.persist(locker);
        // insert member, update locker
        em.persist(member);
    }

    public static void manyToManyTestSave(EntityManager em){

        // 1. 다대다 단방향

        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");

        em.persist(productA);


        com.jpa.demo.relationMappingDifference.manyToMany.domain.Member member = new com.jpa.demo.relationMappingDifference.manyToMany.domain.Member();

        member.setId("member1");
        member.setUsername("회원1");
        member.getProducts().add(productA);

        em.persist(member);
    }

    public static void manyToManyTestFind(EntityManager em){
        com.jpa.demo.relationMappingDifference.manyToMany.domain.Member member = em.find(com.jpa.demo.relationMappingDifference.manyToMany.domain.Member.class, "member1");

        List<Product> products = member.getProducts();

        for (Product product : products) {
            System.out.println("product.name = " + product.getName());
        }
    }


    // 다대다 양방향
//    public static void findInverse(EntityManager em){
//        Product product = em.find(Product.class, "productA");
//        List<com.jpa.demo.relationMappingDifference.manyToMany.domain.Member> members = product.getMembers();
//        for (com.jpa.demo.relationMappingDifference.manyToMany.domain.Member member : members){
//            System.out.println("member = " + member.getUsername());
//        }
//    }

    public static void InteractiveSaveTest(EntityManager em){
        
        // 회원저장
        com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain.Member member = new com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain.Member();
        member.setId("member3");
        member.setUsername("회원3");
        em.persist(member);
        
        
        // 상품저장
        com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain.Product productA = new com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain.Product();
        productA.setId("productC");
        productA.setName("상품C");
        em.persist(productA);


        // 회원상품 저장
        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member);
        memberProduct.setProduct(productA);
        memberProduct.setOrderAmount(2);

        em.persist(memberProduct);

    }

    public static void interactiveFindTest(EntityManager em){
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember("member1");
        memberProductId.setProduct("productA");

        // 1
        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);

        com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain.Member member = memberProduct.getMember();

        com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain.Product product = memberProduct.getProduct();


        // 2
        for (MemberProduct memberProduct1 : member.getMemberProducts()){
            System.out.println("member = " + memberProduct1.getMember().getUsername());
        }

        // 3
        for (MemberProduct memberProduct1 : product.getMemberProducts()){
            System.out.println("product = " + memberProduct1.getProduct().getName());
        }

        // System.out.println("member = " + member.getUsername());
        // System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + memberProduct.getOrderAmount());
    }

    public static void complexDBVersionSaveTest(EntityManager em){
        com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDBVersionDomain.Member member = new com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDBVersionDomain.Member();
        member.setId("memberA");
        member.setUsername("회원A");
        em.persist(member);

        com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDBVersionDomain.Product product = new com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDBVersionDomain.Product();

        product.setId("product");
        product.setName("기본상품");
        em.persist(product);

        Order order = new Order();
        order.setMember(member);
        order.setProduct(product);
        order.setOrderAmount(5);
        em.persist(order);
    }

    public static void complexDBVersionFindTest(EntityManager em){
        Long id = 1L;
        Order order = em.find(Order.class, id);

        com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDBVersionDomain.Member member = order.getMember();
        com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDBVersionDomain.Product product = order.getProduct();

        System.out.println("member = " + member.getUsername());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + order.getOrderAmount());
    }




    





}
