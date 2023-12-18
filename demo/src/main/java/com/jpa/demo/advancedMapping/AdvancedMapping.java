package com.jpa.demo.advancedMapping;

import com.jpa.demo.advancedMapping.joinStrategyDomain.Album;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Book;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Item;
import com.jpa.demo.advancedMapping.joinStrategyDomain.Movie;
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
        implStrategySaveTest(em);

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



}
