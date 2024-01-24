package com.jpa.demo.keyStrategy;

import com.jpa.demo.keyStrategy.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KeyStrategyMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

//    public static void main(String[] args) {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//        // tableStrategy(em);
//        // autoStrategy(em);
//        sequenceStrategy(em);
//        // primaryKeyTest(em);
//        tx.commit();
//    }

    public static void sequenceStrategy(EntityManager em){
        Board board = new Board();
        board.setData("내용");
        em.persist(board);
    }


    // AUTO 전략
    private static void autoStrategy(EntityManager em){
        Board board = new Board();
        em.persist(board);
        System.out.println("board.id : " + board.getId());
    }


    // TABLE 전략
    private static void tableStrategy(EntityManager em){
        Board board = new Board();
        em.persist(board);
        System.out.println("board.id : " + board.getId());

    }

    // 기본키 직접할당
    private static void primaryKeyAllocation(EntityManager em){
        // 기본 키 직접할당
        Board board = Board.builder()
                .id(1L)
                .data("data")
                .build();

    }

    // IDENTITY, SEQUENCE 전략 사용
    // IDENTITY : 데이터베이스에게 맡기는 것
    // SEQUENCE : 시퀀스 조회 후 영속성 엔티티에 저장 (데이터베이스와 2번 통신)
    private static void primaryKeyTest(EntityManager em){
        Board board = new Board();
        // 트랜잭션이 지원하는 쓰기 지연 X
        em.persist(board);
        System.out.println("board.id : " + board.getId());
    }
}
