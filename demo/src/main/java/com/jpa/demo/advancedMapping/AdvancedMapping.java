package com.jpa.demo.advancedMapping;

import com.jpa.demo.advancedMapping.embeddedIdentDomain.ChildId;
import com.jpa.demo.advancedMapping.embeddedIdentDomain.GrandChildId;
import com.jpa.demo.advancedMapping.idClassIdentDomain.GrandChild;
import com.jpa.demo.advancedMapping.identDomain.Child;
import com.jpa.demo.advancedMapping.identDomain.Parent;
import com.jpa.demo.advancedMapping.identDomain.ParentId;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Album;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Book;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Movie;
import com.jpa.demo.advancedMapping.onToOneIdentDomain.Board;
import com.jpa.demo.advancedMapping.onToOneIdentDomain.BoardDetail;
import com.jpa.demo.advancedMapping.superDomain.Member;
import com.jpa.demo.advancedMapping.superDomain.Seller;
import com.jpa.demo.advancedMapping.test.Locker;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

        // idClassIdentSaveTest(em);
        // embeddedIdentSaveTest(em);

        // noIdentSaveTest(em);
        // oneToOneIdentSaveTest(em);

        // oneToManyJoinTableSaveTest(em);
        // manyToManyTableMappingSaveTest(em);
        // manyToManyJoinTableMappingSaveTest(em);
        // oneEntitySaveTest(em);
        testSaved(em);
        et.commit();
    }

    public static void testSaved(EntityManager em){
        Locker locker = new Locker();
        locker.setName("사물함1");

        com.jpa.demo.advancedMapping.test.Member member = new com.jpa.demo.advancedMapping.test.Member();
        member.setUsername("멤버1");
        member.setLocker(locker);

        em.persist(member);
        em.persist(locker);
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

        Seller seller = new Seller();
        seller.setName("판매사원A");
        seller.setShopName("구멍가게");

        em.persist(member);
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

    // 1. @EmbeddedId 식별관계
    public static void embeddedIdentSaveTest(EntityManager em){
        com.jpa.demo.advancedMapping.embeddedIdentDomain.Parent parent = new com.jpa.demo.advancedMapping.embeddedIdentDomain.Parent();
        parent.setId("부모키");
        parent.setName("부모");


        em.persist(parent);


        ChildId childId = new ChildId("자식키");

        com.jpa.demo.advancedMapping.embeddedIdentDomain.Child child = new com.jpa.demo.advancedMapping.embeddedIdentDomain.Child();
        child.setId(childId);
        child.setName("자식");
        child.setParent(parent);

        em.persist(child);

        GrandChildId grandChildId = new GrandChildId("손자키");
        com.jpa.demo.advancedMapping.embeddedIdentDomain.GrandChild grandChild = new com.jpa.demo.advancedMapping.embeddedIdentDomain.GrandChild();
        grandChild.setChild(child);
        grandChild.setId(grandChildId);
        grandChild.setName("손자");

        em.persist(grandChild);
    }

    // 2. 비식별관계
    public static void noIdentSaveTest(EntityManager em){

        com.jpa.demo.advancedMapping.noIdentDomain.Parent parent = new com.jpa.demo.advancedMapping.noIdentDomain.Parent();
        parent.setName("부모");
        em.persist(parent);

        com.jpa.demo.advancedMapping.noIdentDomain.Child child = new com.jpa.demo.advancedMapping.noIdentDomain.Child();
        child.setName("자식");
        child.setParent(parent);

        em.persist(child);

        com.jpa.demo.advancedMapping.noIdentDomain.GrandChild grandChild = new com.jpa.demo.advancedMapping.noIdentDomain.GrandChild();
        grandChild.setChild(child);
        grandChild.setName("손자");
        em.persist(grandChild);
    }

    public static void oneToOneIdentSaveTest(EntityManager em){
        Board board = new Board();
        board.setTitle("제목");
        em.persist(board);


        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setBoard(board);
        boardDetail.setContent("내용");

        em.persist(boardDetail);
    }

    
    // 1. 일대일 매핑 => JoinTable을 사용해서 만듦

    public static void oneToOneJoinTableSaveTest(EntityManager em){

        com.jpa.demo.advancedMapping.oneToOneJoinTableMappingDomain.Child child = new com.jpa.demo.advancedMapping.oneToOneJoinTableMappingDomain.Child();
        child.setName("자식");

        com.jpa.demo.advancedMapping.oneToOneJoinTableMappingDomain.Parent parent = new com.jpa.demo.advancedMapping.oneToOneJoinTableMappingDomain.Parent();
        parent.setName("부모");
        parent.setChild(child);
        em.persist(parent);

        
        child.setParent(parent);
        em.persist(child);
    }

    // 2. 일대다 JoinTable 사용해서 만듦

    public static void oneToManyJoinTableSaveTest(EntityManager em){

        com.jpa.demo.advancedMapping.oneToManyJoinTableDomain.Parent parent = new com.jpa.demo.advancedMapping.oneToManyJoinTableDomain.Parent();
        parent.setName("부모");

        com.jpa.demo.advancedMapping.oneToManyJoinTableDomain.Child childA = new com.jpa.demo.advancedMapping.oneToManyJoinTableDomain.Child();
        childA.setName("자식A");

        com.jpa.demo.advancedMapping.oneToManyJoinTableDomain.Child childB = new com.jpa.demo.advancedMapping.oneToManyJoinTableDomain.Child();
        childB.setName("자식B");

        parent.getChildes().add(childA);
        parent.getChildes().add(childB);

        em.persist(parent);
        em.persist(childA);
        em.persist(childB);

    }

    // 2. 다대일 조인테이블
    public static void manyToManyTableMappingSaveTest(EntityManager em){
        com.jpa.demo.advancedMapping.manyToOneTableMappingDomain.Child childA = new com.jpa.demo.advancedMapping.manyToOneTableMappingDomain.Child();
        childA.setName("자식A");

        com.jpa.demo.advancedMapping.manyToOneTableMappingDomain.Child childB = new com.jpa.demo.advancedMapping.manyToOneTableMappingDomain.Child();
        childB.setName("자식A");


        com.jpa.demo.advancedMapping.manyToOneTableMappingDomain.Parent parent = new com.jpa.demo.advancedMapping.manyToOneTableMappingDomain.Parent();
        parent.setName("부모");

        childA.setParent(parent);
        childB.setParent(parent);

        em.persist(parent);
        em.persist(childA);
        em.persist(childB);
    }


    // 1. 다대다 조인테이블
    public static void manyToManyJoinTableMappingSaveTest(EntityManager em){
        com.jpa.demo.advancedMapping.manyToManyJoinTableDomain.Parent parent = new com.jpa.demo.advancedMapping.manyToManyJoinTableDomain.Parent();
        parent.setName("부모");


        com.jpa.demo.advancedMapping.manyToManyJoinTableDomain.Child childA = new com.jpa.demo.advancedMapping.manyToManyJoinTableDomain.Child();
        childA.setName("자식A");

        com.jpa.demo.advancedMapping.manyToManyJoinTableDomain.Child childB = new com.jpa.demo.advancedMapping.manyToManyJoinTableDomain.Child();
        childB.setName("자식B");

        List<com.jpa.demo.advancedMapping.manyToManyJoinTableDomain.Child> children = new ArrayList<>();
        children.add(childA);
        children.add(childB);

        parent.setChildren(children);

        em.persist(parent);
        em.persist(childA);
        em.persist(childB);
    }


    // 2. 하나의 엔티티 안에 여러개의 테이블을 생성하는 방법 : 권장 X
    public static void oneEntitySaveTest(EntityManager em){
        com.jpa.demo.advancedMapping.oneEntityDomain.Board board = new com.jpa.demo.advancedMapping.oneEntityDomain.Board();
        board.setContent("내용1");
        board.setTitle("제목A");

        em.persist(board);
    }

}
