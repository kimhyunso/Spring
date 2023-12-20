package com.jpa.demo.advancedMapping;

import com.jpa.demo.advancedMapping.idClassIdentDomain.GrandChild;
import com.jpa.demo.advancedMapping.identDomain.Child;
import com.jpa.demo.advancedMapping.identDomain.Parent;
import com.jpa.demo.advancedMapping.identDomain.ParentId;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Album;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Book;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Item;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Movie;
import com.jpa.demo.advancedMapping.superDomain.Member;
import com.jpa.demo.advancedMapping.superDomain.Seller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AdvancedMapping {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    // 상속 관계 매핑
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();

        et.begin();

        // joinStrategyTestSave(em);
        // singleStrategyDomain(em);
        // implStrategySaveTest(em);
        // mappedSuperClassSaveTest(em);
        // identSaveTest(em);

        // identFindTest(em);
        // embeddedSaveTest(em);
        // embeddedFindTest(em);

        idClassIdentSaveTest(em);


        et.commit();
    }

    // 1. 조인 전략
    public static void joinStrategyTestSave(EntityManager em){

        Album album = new Album();
        album.setArtist("앨범1");
        album.setName("앨범상품");
        album.setPrice(4000);
        em.persist(album);



        Movie movie = new Movie();
        movie.setActor("배우C");
        movie.setDirector("감독A");

        movie.setName("영화상품");
        movie.setPrice(3000);

        em.persist(movie);


        Book book = new Book();
        book.setAuthor("저자A");
        book.setIsbn("978-89-6077");

        book.setName("책상품");
        book.setPrice(10000);

        em.persist(book);
    }
    
    // 2. 단일 테이블 전략
    public static void singleStrategyDomain(EntityManager em){

        com.jpa.demo.advancedMapping.singleStrategyDomain.Album album = new com.jpa.demo.advancedMapping.singleStrategyDomain.Album();
        album.setArtist("앨범1");
        album.setName("앨범상품");
        album.setPrice(4000);
        em.persist(album);



        com.jpa.demo.advancedMapping.singleStrategyDomain.Movie movie = new com.jpa.demo.advancedMapping.singleStrategyDomain.Movie();
        movie.setActor("배우C");
        movie.setDirector("감독A");

        movie.setName("영화상품");
        movie.setPrice(3000);

        em.persist(movie);


        com.jpa.demo.advancedMapping.singleStrategyDomain.Book book = new com.jpa.demo.advancedMapping.singleStrategyDomain.Book();
        book.setAuthor("저자A");
        book.setIsbn("978-89-6077");

        book.setName("책상품");
        book.setPrice(10000);
        em.persist(book);
    }
    
    // 3. 구현 클래스마다 테이블 전략 :: 추천 x
    public static void implStrategySaveTest(EntityManager em){
        com.jpa.demo.advancedMapping.implStrategyDomain.Album album = new com.jpa.demo.advancedMapping.implStrategyDomain.Album();
        album.setArtist("앨범B");
        album.setName("앨범상품");
        album.setPrice(3000);
        em.persist(album);

        com.jpa.demo.advancedMapping.implStrategyDomain.Book book = new com.jpa.demo.advancedMapping.implStrategyDomain.Book();
        book.setAuthor("저자C");
        book.setIsbn("9781-456");
        book.setName("책상품");
        book.setPrice(5000);

        em.persist(book);

        com.jpa.demo.advancedMapping.implStrategyDomain.Movie movie = new com.jpa.demo.advancedMapping.implStrategyDomain.Movie();
        movie.setActor("배우R");
        movie.setDirector("감독C");
        movie.setName("영화상품");
        movie.setPrice(2000);

        em.persist(movie);
    }
    ////////////////////////////////////////////////////////////////////////////
    // 1. mappedSuperClass : 공통정보만 매핑하고 테이블 필요없을때 사용
    public static void mappedSuperClassSaveTest(EntityManager em){
        Member member = new Member();
        member.setName("회원A");
        member.setEmail("user@naver.com");


        em.persist(member);


        Seller seller = new Seller();
        seller.setName("판매사원A");
        seller.setShopName("구멍가게");


        em.persist(seller);
    }


    // 2. 비식별자 복합키 :: @IdClass 사용
    public static void identSaveTest(EntityManager em){
        Parent parent = new Parent();
        parent.setId1("myId1");
        parent.setId2("myId2");
        parent.setName("parentName");
        em.persist(parent);

        Child child = new Child();
        child.setId("childId1");
        child.setParent(parent);
        em.persist(child);
    }

    public static void identFindTest(EntityManager em){
        ParentId parentId = new ParentId("myId1", "myId2");

        Parent parent = em.find(Parent.class, parentId);

        System.out.println("parentName = " + parent.getName());

    }

    ///////////////////////////////////
    // 1. @EmbeddedId
    public static void embeddedSaveTest(EntityManager em){
        com.jpa.demo.advancedMapping.embeddedDomain.Parent parent = new com.jpa.demo.advancedMapping.embeddedDomain.Parent();
        com.jpa.demo.advancedMapping.embeddedDomain.ParentId parentId = new com.jpa.demo.advancedMapping.embeddedDomain.ParentId("myId1", "myId2");

        parent.setId(parentId);
        parent.setName("parentName");
        em.persist(parent);
    }

    public static void embeddedFindTest(EntityManager em){

        com.jpa.demo.advancedMapping.embeddedDomain.ParentId parentId = new com.jpa.demo.advancedMapping.embeddedDomain.ParentId("myId1", "myId2");

        com.jpa.demo.advancedMapping.embeddedDomain.Parent parent = em.find(com.jpa.demo.advancedMapping.embeddedDomain.Parent.class, parentId);

        System.out.println("Name = " + parent.getName());
    }

    // 2. 복합키 - 식별관계매핑 - IdClass
    public static void idClassIdentSaveTest(EntityManager em){
        com.jpa.demo.advancedMapping.idClassIdentDomain.Parent parent = new com.jpa.demo.advancedMapping.idClassIdentDomain.Parent();
        parent.setId("parentA");
        parent.setName("부모A");

        em.persist(parent);

        com.jpa.demo.advancedMapping.idClassIdentDomain.Child child = new com.jpa.demo.advancedMapping.idClassIdentDomain.Child();
        child.setChildId("부모A자식");
        child.setName("자식A");
        child.setParent(parent);

        em.persist(child);

        GrandChild grandChild = new GrandChild();
        grandChild.setId("자식A손자");
        grandChild.setChild(child);
        grandChild.setName("손자A");

        em.persist(grandChild);
    }






}
