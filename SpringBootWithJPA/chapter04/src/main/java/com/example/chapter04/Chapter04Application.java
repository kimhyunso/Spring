package com.example.chapter04;

import com.example.chapter04.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Chapter04Application {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			// tableStrategy(em);
			// autoStrategy(em);
			sequenceStrategy(em);
			// primaryKeyTest(em);
			tx.commit();
		}catch (Exception e){
			System.out.println("Error : " + e.getMessage());
		}
    }

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
