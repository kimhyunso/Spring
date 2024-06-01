package com.example.chapter05;

import com.example.chapter05.domain.Member;
import com.example.chapter05.domain.NotJPAMember;
import com.example.chapter05.domain.NotJPATeam;
import com.example.chapter05.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

public class Chapter05Application {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction ex = em.getTransaction();
		try{
			ex.begin();

			// notJpaLogic();
			// testSave(em);
			// findTeam(em);
			// queryLogicJoin(em);

			// updateRelation(em);
			// deleteRelation(em);
			// biDirection(em);

			// testSaveNonOwner(em);
			// bindingDirection(em);

			// test순수한_양방향();
			// testORM_양방향(em);
			// testORM_양방향리팩토링(em);
			// 일대일단방향Test(em);
			find양방향Test(em);
			ex.commit();
		}catch (Exception e){
			System.out.println("Error : " + e.getMessage());
			ex.rollback();
		}
    }


	public static void 단방향_양방향_Test(EntityManager em){

		Team team = new Team();
		team.setId("team1");
		team.setName("A팀");

		Member member = new Member();
		member.setId("member1");
		member.setUsername("회원A");
		member.setTeam(team);

		em.persist(team);
		em.persist(member);
	}

	public static void find양방향Test(EntityManager em){
		Team team = em.find(Team.class, "team1");

		for (Member member : team.getMembers()){
			System.out.println(member.getUsername());
		}
	}


	private static void notJpaLogic(){
		NotJPAMember member1  = NotJPAMember.builder()
				.id("member1")
				.username("화원1")
				.build();

		NotJPAMember member2  = NotJPAMember.builder()
				.id("member2")
				.username("화원2")
				.build();

		NotJPATeam team = NotJPATeam.builder()
				.id("team1")
				.name("팀1")
				.build();

		member1.setTeam(team);
		member2.setTeam(team);

		// 객체 그래프 탐색
		NotJPATeam findTeam = member1.getTeam();
		System.out.println("findTeam.id : " + findTeam.getId() + ", Name : " + findTeam.getName());
	}

	private static void testSave(EntityManager em){
		Team team = Team.builder()
				.id("team1")
				.name("팀1")
				.build();


		em.persist(team);


		Member member1 = Member.builder()
				.id("member1")
				.username("회원1")
				.team(team) // 연관관계 설정 member1 -> team1
				.build();
		em.persist(member1);

		Member member2 = Member.builder()
				.id("member2")
				.username("회원2")
				.team(team) // 연관관계 설정 member2 -> team1
				.build();
		em.persist(member2);

	}

	private static void findTeam(EntityManager em){
		Member member = em.find(Member.class, "member1");
		// 객체 그래프 탐색
		Team team = member.getTeam();
		System.out.println("팀 이름 : " + team.getName());
	}

	private static void queryLogicJoin(EntityManager em){
		String jpql = "select m from Member m join m.team t where " +
				"t.name=:teamName";

		// 파라미터를 바인딩 받는 문법 =:
		// MEMBER TABLE과 TEAM TABLE이 조인된 결과=(MEMBER 컬럼들만)를 가져옴
		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();

		for (Member member : resultList){
			System.out.println("[query] member team : " + member.getTeam().getName());
			System.out.println("[query] member.username : " + member.getUsername());
		}
	}

	private static void updateRelation(EntityManager em){

		Team team = Team.builder()
				.id("team2")
				.name("팀2")
				.build();

		em.persist(team);

		Member member = em.find(Member.class, "member1");
		member.setTeam(team);
	}
	// 연관관계 제거
	private static void deleteRelation(EntityManager em){
		Member member = em.find(Member.class, "member1");
		Team team = member.getTeam();
		// null로 만들고 삭제해야함 (제약조건)
		member.setTeam(null);
		em.remove(team);
	}

	// 양방향 연관관계
	public static void biDirection(EntityManager em){
		Team team = em.find(Team.class, "team1");
		Member findMember = em.find(Member.class, "member1");

		List<Member> members = team.getMembers();

		for (Member member : members){
			System.out.println("member.username = " + member.getUsername());
		}
	}


	// 양방향 연관관계 저장
	public static void bindingDirection(EntityManager em){
		Team team = Team.builder()
				.id("team3")
				.name("팀3")
				.build();

		em.persist(team);

		Member member = Member.builder()
				.team(team)
				.id("member4")
				.username("회원4")
				.build();

		em.persist(member);

		Member member1 = Member.builder()
				.id("member5")
				.username("회원5")
				.team(team)
				.build();

		em.persist(member1);

	}

	public static void testSaveNonOwner(EntityManager em){
		Member member = Member.builder()
				.id("member1")
				.username("회원1")
				.build();
		em.persist(member);

		Member member1 = Member.builder()
				.id("member2")
				.username("회원2")
				.build();
		em.persist(member1);

		Team team = Team.builder()
				.id("team1")
				.name("팀1")
				.build();

		team.getMembers().add(member);
		team.getMembers().add(member1);
		em.persist(team);

	}

	public static void test순수한_양방향(){

		// 팀1
		Team team = Team.builder()
				.id("team1")
				.name("팀1")
				.build();


		Member member1 = Member.builder()
				.id("member1")
				.username("회원1")
				.team(team)
				.build();

		Member member2 = Member.builder()
				.id("member2")
				.username("회원2")
				.team(team)
				.build();

		team.getMembers().add(member1);
		team.getMembers().add(member2);

		List<Member> members = team.getMembers();
		System.out.println("member.size = " + members.size());
	}


	public static void testORM_양방향(EntityManager em){
		Team team = Team.builder()
				.id("team1")
				.name("팀1")
				.build();

		em.persist(team);

		// 양방향 연관관계 설정
		// 연관관계 주인
		Member member1 = Member.builder()
				.id("member1")
				.username("회원1")
				.team(team)
				.build();

		// 연관관계 주인 아님 저장시 사용 안됨
		team.getMembers().add(member1);
		em.persist(member1);

		// 양방향 연관관계 설정
		Member member2 = Member.builder()
				.id("member2")
				.username("회원2")
				.team(team)
				.build();

		// 연관관계 주인 아님 저장시 사용 안됨
		team.getMembers().add(member2);
		em.persist(member2);

		em.remove(member1);
		em.remove(member2);
		em.remove(team);

	}

	public static void testORM_양방향리팩토링(EntityManager em){
		Team team = Team.builder()
				.id("team1")
				.name("팀1")
				.build();

		em.persist(team);

		// 양방향 연관관계 설정
		// 연관관계 주인
		Member member1 = Member.builder()
				.id("member1")
				.username("회원1")
				.build();
		member1.setTeam(team);

		// 연관관계 주인 아님 저장시 사용 안됨
		// team.getMembers().add(member1);
		em.persist(member1);

		// 양방향 연관관계 설정
		Member member2 = Member.builder()
				.id("member2")
				.username("회원2")
				.build();
		member2.setTeam(team);

		// 연관관계 주인 아님 저장시 사용 안됨
		// team.getMembers().add(member2);
		em.persist(member2);

//        em.remove(member1);
//        em.remove(member2);
//        em.remove(team);

	}
}
