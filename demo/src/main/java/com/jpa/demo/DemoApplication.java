package com.jpa.demo;

import com.jpa.demo.domainTest.Member;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
	// CRUD logic
	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);

		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();

		try{
			tx.begin();
			// logic(em);
			// testDetached(em);
			testJPQL(em);
			tx.commit();
		}catch (Exception e){
			tx.rollback();
		}finally {
			// 영속성 컨텍스트 종료
			em.close();
		}
		emf.close();
	}


	private static void testJPQL(EntityManager em){
		Member memberA = Member.builder()
				.id("memberA")
				.age(23)
				.name("회원A")
				.build();

		Member memberB = Member.builder()
				.id("memberB")
				.age(13)
				.name("회원B")
				.build();

		Member memberC = Member.builder()
				.id("memberC")
				.age(5)
				.name("회원C")
				.build();

		em.persist(memberA);
		em.persist(memberB);
		em.persist(memberC);

		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		for (Member member : members){
			System.out.println("Member : " + member.getId() +  " Name : " + member.getName() + " Age : "+  member.getAge());
		}
		// 영속성 컨텍스트 종료로 인해 MemberA, MemberB, MemberC가 관리되지 않는다.
		em.close();

		Member findMember = em.find(Member.class, "memberA");
		System.out.println("findMember : " + findMember.getId() + " Age : " + findMember.getAge() + " Name : " + findMember.getName());

		// 영속성 컨텍스트 초기화
		em.clear();
		// 바뀌지 않음
		memberA.setName("changeName");
	}


	private static void logic(EntityManager em){
		String id = "id1";
		Member member = Member.builder()
				.id(id)
				.age(2)
				.name("지한")
				.build();

		// 저장
		em.persist(member);

		// 수정
		member.setAge(20);

		// 단일조회
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember = " + findMember.getName() + " age = " + findMember.getAge());

		// 다수조회
		List<Member> members =
				em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("member.size = " + members.size());

		// 삭제
		em.remove(members);

	}

}
