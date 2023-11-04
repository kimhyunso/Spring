package com.jpa.demo;

import com.jpa.demo.domain.Member;
import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

	public static void main(String[] args) {
		Member member = createMember("memberA", "회원1");

		member.setName("회원명변경");
		mergeMember(member);
	}

	private static Member createMember(String id, String name){
		Member member = Member.builder()
				.id(id)
				.age(20)
				.name(name)
				.build();

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		em.persist(member);
		tx.commit();

		em.close();

		return member;

	}

	private static void mergeMember(Member member){
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		Member mergeMember = em.merge(member);
		tx.commit();
		
		// 준영속상태
		System.out.println("Member : " + member.getId() + " Age : " + member.getAge() + " Name : " + member.getName());
		
		
		// 영속상태

		System.out.println("mergeMember : " + mergeMember.getId() + " Age : " + mergeMember.getAge() + " Name : " + mergeMember.getName());

		System.out.println("em contains member : " + em.contains(member));
		System.out.println("em contains mergeMember : " + em.contains(mergeMember));

		em.close();

	}

	/*public static void main(String[] args) {
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
	}*/

	private static void testDetached(EntityManager em){
		Member member = Member.builder()
				.id("memberA")
				.name("회원A")
				.build();

		// 회원 엔티티 영속상태
		em.persist(member);

		// 회원 엔티티를 영속성 컨텍스트에서 분리
		em.detach(member); // 준영속상태
		// 데이터베이스에 반영 안됨
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
