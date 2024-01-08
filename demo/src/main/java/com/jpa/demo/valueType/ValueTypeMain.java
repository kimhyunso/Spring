package com.jpa.demo.valueType;

import com.jpa.demo.valueType.domain2.Address;
import com.jpa.demo.valueType.domain2.Member;
import com.jpa.demo.valueType.domain2.Period;
import com.jpa.demo.valueType.domain3.PhoneNumber;
import com.jpa.demo.valueType.domain3.PhoneServiceProvider;
import com.jpa.demo.valueType.domain3.Zipcode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Date;

public class ValueTypeMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            // embeddedSave(em);
            embeddedMemberSave(em);

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

        com.jpa.demo.valueType.domain3.Address newAddress = member.getAddress();
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


}
