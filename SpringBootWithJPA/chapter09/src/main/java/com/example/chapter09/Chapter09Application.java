package com.example.chapter09;

import com.example.chapter09.domain.domain3.PhoneNumber;
import com.example.chapter09.domain.domain3.PhoneServiceProvider;
import com.example.chapter09.domain.domain3.Zipcode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

public class Chapter09Application {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			// embeddedSave(em);
			// embeddedMemberSave(em);
			// collectionSave(em);
			// collectionFind(em);
			collectionUpdate(em);
			tx.commit();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
		}
	}

	public static void embeddedSave(EntityManager em){
		Address address = Address.builder()
				.city("seoul")
				.street("test")
				.zipcode("171-171")
				.build();

		Member member = new Member();
		member.setName("멤버1");
		member.setHomeAddress(address);
		member.setWorkPeriod(new Period());

		em.persist(member);
	}

	public static void embeddedMemberSave(EntityManager em){

		Zipcode zipcode = Zipcode.builder()
				.zip("01000")
				.plusFour("상세주소")
				.build();

		com.jpa.demo.valueType.domain3.Address address  = com.jpa.demo.valueType.domain3.Address.builder()
				.city("seoul")
				.state("test")
				.street("test")
				.zipcode(zipcode)
				.build();

		PhoneServiceProvider provider = PhoneServiceProvider.builder()
				.name("phoneProvider1")
				.build();

		PhoneNumber phoneNumber = PhoneNumber.builder()
				.areaCode("082")
				.localNumber("010-1234-1234")
				.provider(provider)
				.build();

		com.jpa.demo.valueType.domain3.Member member = new com.jpa.demo.valueType.domain3.Member();
		member.setAddress(address);
		member.setPhoneNumber(phoneNumber);

		com.jpa.demo.valueType.domain3.Address newAddress = (com.jpa.demo.valueType.domain3.Address) member.getAddress().clone();
		newAddress.setCity("newCity");

		com.jpa.demo.valueType.domain3.Member member2 = new com.jpa.demo.valueType.domain3.Member();
		member2.setAddress(newAddress);
		member2.setPhoneNumber(phoneNumber);


		em.persist(provider);
		em.persist(member);
		em.persist(member2);

		// com.jpa.demo.valueType.domain3.Address address1 = member.getAddress();
		// address1.setCity("NewCity");
	}

	public static void collectionSave(EntityManager em){
		com.jpa.demo.valueType.domain4.Member member = new com.jpa.demo.valueType.domain4.Member();
		member.setHomeAddress(new com.jpa.demo.valueType.domain4.Address("통영", "몽돌해수욕장", "660-123"));

		member.getFavoriteFoods().add("짬뽕");
		member.getFavoriteFoods().add("짜장면");
		member.getFavoriteFoods().add("탕수육");

		member.getAddressHistory().add(new com.jpa.demo.valueType.domain4.Address("서울", "강남", "123-123"));
		member.getAddressHistory().add(new com.jpa.demo.valueType.domain4.Address("서울", "강북", "000-000"));

		em.persist(member);
	}

	public static void collectionFind(EntityManager em){
		com.jpa.demo.valueType.domain4.Member member = em.find(com.jpa.demo.valueType.domain4.Member.class, 1L);

		com.jpa.demo.valueType.domain4.Address address = member.getHomeAddress();

		Set<String> favoriteFoods = member.getFavoriteFoods();

		for (String food : favoriteFoods){
			System.out.println(food);
		}

		List<com.jpa.demo.valueType.domain4.Address> addressHistory = member.getAddressHistory();


		for (com.jpa.demo.valueType.domain4.Address addresses : addressHistory){
			System.out.println(addresses.getCity());
			System.out.println(addresses.getState());
			System.out.println(addresses.getStreet());
		}
	}

	public static void collectionUpdate(EntityManager em){
		com.jpa.demo.valueType.domain4.Member member = em.find(com.jpa.demo.valueType.domain4.Member.class, 1L);
		// 1. 임베디드 값 타입 수정
		// 1. select member
		// 4. update member
		member.setHomeAddress(new com.jpa.demo.valueType.domain4.Address("새로운도시", "신도시1", "123456"));
		// 2. 기본값 타입 컬렉션 수정
		// 2. select favorite_foods
		Set<String> favoriteFoods = member.getFavoriteFoods();
		// 7. delete favorite_foods
		favoriteFoods.remove("탕수육");
		// 8. insert favorite_foods
		favoriteFoods.add("치킨");

		// 3. 임베디드 값 타입 컬렉션 수정
		// 3. select address_history
		List<com.jpa.demo.valueType.domain4.Address> addressHistory = member.getAddressHistory();
		// 5. delete address_history
		addressHistory.remove(new com.jpa.demo.valueType.domain4.Address("서울", "강남", "123-123"));
		// 6. insert address_history **2번 ??**
		/**
		 *  값 타입 컬렉션의 보관된 값 타입들은 별도의 테이블에 보관된다.
		 *  따라서 여기에 보관된 값 타입의 값이 변경되면 데이터베이스에 있는 원본 데이터를 찾기 어렵다는 문제가 있다.
		 *
		 *  이런 문제로 인해, JPA 구현체들은 값 타입 컬렉션에 변경 사항이 발생하면,
		 *  값 타입 컬렉션이 매핑된 테이블의 연관된 모든 데이터를 삭제하고,
		 *  현재 값 타입 컬렉션 객체에 있는 모든 값을 데이터베이스에 다시 저장한다.
		 */
		addressHistory.add(new com.jpa.demo.valueType.domain4.Address("새로운도시", "새로운주소", "123-567"));
	}

}
