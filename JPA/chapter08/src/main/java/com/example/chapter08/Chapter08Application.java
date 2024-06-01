package com.example.chapter08;

import com.example.chapter08.domain.domain3.Order;
import com.example.chapter08.domain.domain3.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

public class Chapter08Application {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");


	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// proxySaveTest(em);

		Long findMemberId = 1L;

		// printUserAndTeam(findMemberId, em);

		// printUser(findMemberId, em);

		// proxyMemberFind(findMemberId, em);
		Long findTeam = 1L;
		// proxyTeamFind(findTeam, em);

		// poxyFinds(findMemberId, findTeam, em);
		// eagerLoadingSaveTest(em);
		// eagerLoadingFindTest(findMemberId, em);
		// lazyLoadingFindTest(findMemberId, em);
		// proxyPracticeSaveTest(em);
		String findMemberIdStr = "member3";

		// memberAndTeamFind(em, findMemberIdStr);

		// saveNoCascade(em);
		// saveWithCascade(em);
		// removeNoCascade(em);
		// removeWithCascade(em);

		// saved(em);
		orphanRemoval(em);

		tx.commit();
		em.close();

		// ERROR
		// System.out.println("준영속 상태 초기화 시도 : " + member.getUsername());
	}


	public static void proxySaveTest(EntityManager em){
		Team teamLove = Team.builder()
				.name("좋아요팀")
				.build();


		Member memberLoveA = Member.builder()
				.username("좋아요멤버A")
				.team(teamLove)
				.build();


		Member memberLoveB = Member.builder()
				.username("좋아요멤버B")
				.team(teamLove)
				.build();

		Team teamHate = Team.builder()
				.name("싫어요팀")
				.build();

		Member memberHateA = Member.builder()
				.team(teamHate)
				.username("싫어요멤버A")
				.build();

		Member memberHateB = Member.builder()
				.team(teamHate)
				.username("싫어요멤버B")
				.build();


		em.persist(teamLove);
		em.persist(teamHate);
		em.persist(memberLoveA);
		em.persist(memberLoveB);
		em.persist(memberHateA);
		em.persist(memberHateB);
	}




	public static void printUserAndTeam(Long memberId, EntityManager em){
		Member member = em.find(Member.class, memberId);
		// 팀정보를 갖고옴
		Team team = member.getTeam();

		System.out.println("회원이름 : " + member.getUsername());
		System.out.println("소속팀 : " + team.getName());
	}

	public static void printUser(Long memberId, EntityManager em){

		// Member를 조회할 때, Team까지 조회하는 것은 효율적이지 않음

		Member member = em.find(Member.class, memberId);
		/**
		 *  실제 엔티티가 사용될때, (member.getTeam() 같은 경우)
		 *  그 엔티티를 조회하는 경우 (team select) ==> 지연로딩
		 *
		 * */
		// member.getTeam();

		System.out.println("회원이름 : " + member.getUsername());
	}

	public static void proxyMemberFind(Long memberId, EntityManager em){
		// 지연로딩됨
		Member member = em.getReference(Member.class, memberId);
		// 실제 사용을 할 때, select함 :: 프록시 객체의 초기화
		// 1. getName();
		System.out.println("회원이름 : " + member.getUsername());
	}

	public static void proxyTeamFind(Long teamId, EntityManager em){
		// 즉시로딩
		// Team team = em.find(Team.class, teamId);

		// 지연로딩
		Team team = em.getReference(Team.class, teamId);
		// 프록시 초기화
		System.out.println("팀 아이디 : " + team.getId());
		// System.out.println("팀 이름 : " + team.getName());
		// 1. SELECT를 하지 않음 : 영속성 컨텍스트가 DB에 접근하지 않음
		// 2. 프록시 객체가 초기화 되지 않음
		// System.out.println("팀 PK : " + team.getId());
		// System.out.println("팀이름 : " + team.getName());
	}

	public static void poxyFinds(Long memberId, Long teamId, EntityManager em){
		Member member = em.find(Member.class, memberId);
		Team team = em.getReference(Team.class, teamId);
		// Team team = em.find(Team.class, teamId);
		// 사용을 하게됨 => ProxyEntity 초기화
		member.setTeam(team);

		/**
		 * find로 select, update
		 * 1. member find => select 1
		 * 2. team find => select 2
		 * 3. setTeam => update 1
		 */

		/**
		 * getReference로 select, update
		 * 1. member find => select 1
		 * 2. setTeam => update 1
		 */

		// proxy 초기화 여부 확인
		boolean isLoad = em.getEntityManagerFactory()
				.getPersistenceUnitUtil().isLoaded(team);

		// boolean isLoad = emf.getPersistenceUnitUtil().isLoaded(entity);

		System.out.println("isLoad : " + isLoad);
		System.out.println("teamProxy = " + team.getClass().getName());
	}

	public static void test(EntityManager em){
		/**
		 *  1. member + team 같이 select 하는 것이 좋을까 :: 즉시로딩
		 *  2. member select => team 사용시점에 select를 하는 것이 좋을까 :: 지연로딩
		 */

		Member member = em.find(Member.class, 1L);
		// 객체그래프탐색
		Team team = member.getTeam();
		System.out.println("팀이름 : " + team.getName());

	}

	public static void eagerLoadingSaveTest(EntityManager em){

		com.jpa.demo.proxy.Domain2.Team teamLove = com.jpa.demo.proxy.Domain2.Team.builder()
				.name("좋아요팀")
				.build();


		com.jpa.demo.proxy.Domain2.Member memberLoveA = com.jpa.demo.proxy.Domain2.Member.builder()
				.username("좋아요멤버A")
				.team(teamLove)
				.build();


		com.jpa.demo.proxy.Domain2.Member memberLoveB = com.jpa.demo.proxy.Domain2.Member.builder()
				.username("좋아요멤버B")
				.team(teamLove)
				.build();

		com.jpa.demo.proxy.Domain2.Team teamHate = com.jpa.demo.proxy.Domain2.Team.builder()
				.name("싫어요팀")
				.build();

		com.jpa.demo.proxy.Domain2.Member memberHateA = com.jpa.demo.proxy.Domain2.Member.builder()
				.team(teamHate)
				.username("싫어요멤버A")
				.build();

		com.jpa.demo.proxy.Domain2.Member memberHateB = com.jpa.demo.proxy.Domain2.Member.builder()
				.team(teamHate)
				.username("싫어요멤버B")
				.build();


		em.persist(teamLove);
		em.persist(teamHate);
		em.persist(memberLoveA);
		em.persist(memberLoveB);
		em.persist(memberHateA);
		em.persist(memberHateB);

	}

	// 1. 즉시로딩
	public static void eagerLoadingFindTest(Long findMemberId, EntityManager em){

		/**
		 *  즉시 로딩
		 *  select join ==> 이미 Team에 대한 데이터가 들어와 있다는 것
		 */
		/**
		 *      select
		 *         m1_0.MEMBER_ID,
		 *         t1_0.TEAM_ID,
		 *         t1_0.name,
		 *         m1_0.username
		 *     from
		 *         Member m1_0
		 *     left join
		 *         Team t1_0
		 *             on t1_0.TEAM_ID=m1_0.TEAM_ID
		 *     where
		 *         m1_0.MEMBER_ID=?
		 */
		com.jpa.demo.proxy.Domain2.Member member = em.find(com.jpa.demo.proxy.Domain2.Member.class, findMemberId);
		com.jpa.demo.proxy.Domain2.Team team = member.getTeam();

		System.out.println(team.getName());
	}

	// 2. 지연로딩
	public static void lazyLoadingFindTest(Long findMemberId, EntityManager em){

		/**
		 *  지연로딩 : Team Entity를 쓰기 전까지 select해오지 않음 :: 초기화 x
		 *
		 *     select
		 *         m1_0.MEMBER_ID,
		 *         m1_0.TEAM_ID,
		 *         m1_0.username
		 *     from
		 *         Member m1_0
		 *     where
		 *         m1_0.MEMBER_ID=?
		 */

		com.jpa.demo.proxy.Domain2.Member member = em.find(com.jpa.demo.proxy.Domain2.Member.class, findMemberId);
		com.jpa.demo.proxy.Domain2.Team team = member.getTeam();
		// ProxyEntity 초기화
		/**
		 *     select
		 *         t1_0.TEAM_ID,
		 *         t1_0.name
		 *     from
		 *         Team t1_0
		 *     where
		 *         t1_0.TEAM_ID=?
		 */
		System.out.println(team.getName());
	}

	public static void proxyPracticeSaveTest(EntityManager em){


		com.jpa.demo.proxy.domain3.Team teamA = com.jpa.demo.proxy.domain3.Team.builder()
				.id("team1")
				.name("A팀")
				.build();

		com.jpa.demo.proxy.domain3.Member memberA = new com.jpa.demo.proxy.domain3.Member();
		memberA.setId("member1");
		memberA.setTeam(teamA);
		memberA.setUsername("회원A");


		com.jpa.demo.proxy.domain3.Team teamB = com.jpa.demo.proxy.domain3.Team.builder()
				.id("team2")
				.name("B팀")
				.build();

		com.jpa.demo.proxy.domain3.Member memberB = com.jpa.demo.proxy.domain3.Member.builder()
				.id("member2")
				.team(teamA)
				.username("회원B")
				.build();



		com.jpa.demo.proxy.domain3.Member memberC = new com.jpa.demo.proxy.domain3.Member();
		memberC.setId("member3");
		memberC.setUsername("회원C");
		memberC.setTeam(teamB);



		Product productFood = Product.builder()
				.id("product1")
				.name("딸기")
				.build();



		Product productMovie = Product.builder()
				.id("product2")
				.name("강산영화")
				.build();



		Product productElectron = Product.builder()
				.id("product3")
				.name("냉장고")
				.build();

		Order orderFood = Order.builder()
				.id("order1")
				.member(memberA)
				.name("음식")
				.product(productFood)
				.build();



		Order orderMovie = Order.builder()
				.id("order2")
				.member(memberB)
				.name("영화")
				.product(productMovie)
				.build();




		Order orderElectron = Order.builder()
				.id("order3")
				.member(memberC)
				.name("가전제품")
				.product(productElectron)
				.build();









		em.persist(teamA);
		em.persist(teamB);
		em.persist(memberA);
		em.persist(memberB);
		em.persist(memberC);
		em.persist(orderFood);
		em.persist(orderMovie);
		em.persist(orderElectron);
		em.persist(productFood);
		em.persist(productMovie);
		em.persist(productElectron);

	}
	// JPA 패치 전략 추천방법 : 지연 로딩을 사용하는 것

	/**
	 *  *필히 알아야할 사항*
	 *  엔티티 안에 컬렉션이 포함되어 있는 경우, LAZY(지연로딩)을 꼭 사용해야함
	 *  이유 : SELECT를 할때, 몇백만건의 데이터가 있는 경우 EAGER(즉시로딩)을 사용하면 전부 들고올 수 있기 때문에
	 */
	// 일대다 관계를 즉시로딩 => outer join (외부조인) 사용
	public static void memberAndTeamFind(EntityManager em, String findMemberId){
		// select
		com.jpa.demo.proxy.domain3.Member member = em.find(com.jpa.demo.proxy.domain3.Member.class, findMemberId);
		System.out.println("팀이름 : " + member.getTeam().getName());
		// 컬렉션매퍼 (PersistentBag)


		List<Order> orders = member.getOrders();

		List<Integer> lists = new ArrayList<>();
		lists.add(1);

		System.out.println("list = " + lists.getClass().getName());

		// 컬렉션 래퍼 (지연로딩일 경우, 엔티티 내의 컬렉션을 추적하고 관리할 목적)
		System.out.println("orders = " + orders.getClass().getName());

		// select
		// member.getOrders().get(0) => Product 같이 따라옴 :: EAGER(즉시로딩)
	}

	public static void saveNoCascade(EntityManager em){

		Parent parent = new Parent();
		em.persist(parent);

		Child child1 = new Child();
		child1.setParent(parent);
		parent.getChildren().add(child1);
		em.persist(child1);

		Child child2 = new Child();
		child2.setParent(parent);
		parent.getChildren().add(child2);
		em.persist(child2);

	}

	public static void removeNoCascade(EntityManager em){
		Parent parent = em.find(Parent.class, 1L);
		Child child1 = em.find(Child.class, 1L);
		Child child2 = em.find(Child.class, 2L);

		em.remove(parent);
		em.remove(child1);
		em.remove(child2);
	}

	public static void removeWithCascade(EntityManager em){
		Parent parent = em.find(Parent.class, 1L);

		em.remove(parent);
	}

	public static void saveWithCascade(EntityManager em){

		Child child1 = new Child();
		Child child2 = new Child();

		Parent parent = new Parent();

		child1.setParent(parent);
		child2.setParent(parent);

		parent.getChildren().add(child1);
		parent.getChildren().add(child2);

		em.persist(parent);
	}


	public static void saved(EntityManager em){
		com.jpa.demo.proxy.domain5.Parent parent = new com.jpa.demo.proxy.domain5.Parent();


		com.jpa.demo.proxy.domain5.Child child1 = new com.jpa.demo.proxy.domain5.Child();
		child1.setParent(parent);

		com.jpa.demo.proxy.domain5.Child child2 = new com.jpa.demo.proxy.domain5.Child();
		child2.setParent(parent);

		parent.getChildren().add(child1);
		parent.getChildren().add(child2);

		em.persist(parent);
		em.persist(child1);
		em.persist(child2);
	}



	public static void orphanRemoval(EntityManager em){
		com.jpa.demo.proxy.domain5.Parent parent = em.find(com.jpa.demo.proxy.domain5.Parent.class, 152L);
		parent.getChildren().remove(1);

		em.remove(parent);
	}

}
