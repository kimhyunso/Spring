package com.example.chapter10;

import com.jpa.demo.queryLanguage.domain1.Member;

import javax.persistence.*;
import java.util.List;

public class nativeQueryMain {
//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
//
//    public static void main(String[] args) {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction ex = em.getTransaction();
//
//        try{
//            ex.begin();
//            // nativeQuerySelect(em);
//            // resultMappingNativeQuery(em);
//            namedNaNativeQuery(em);
//            ex.commit();
//        }catch (Exception e){
//            System.out.println("Error : " + e.getMessage());
//            ex.rollback();
//        }
//
//    }
//
//    public static void nativeQuerySelect(EntityManager em){
//        String sql = "SELECT MEMBER_ID, AGE, NAME, TEAM_ID "
//                   + "FROM MEMBER WHERE AGE > ?";
////
////        // 위치기반 파라미터만 지원한다.
////        List<Member> members = em.createNativeQuery(sql, Member.class)
////                .setParameter(1, 20)
////                .getResultList();
////
////        members.stream().forEach(member -> {
////            System.out.println("회원이름 : " + member.getName() + ", 회원나이 : " + member.getAge());
////        });
//
//        List<Object[]> result = em.createNativeQuery(sql)
//                .setParameter(1, 20)
//                .getResultList();
//
//        result.stream().forEach(row -> {
//            System.out.println("회원이름 : " + row[2] + ", 회원나이 : " + row[1]);
//        });
//    }
//
//    public static void resultMappingNativeQuery(EntityManager em){
//        String sql = "SELECT M.MEMBER_ID, AGE, NAME, TEAM_ID, I.ORDER_COUNT "
//                   + "FROM MEMBER M "
//                   + "LEFT JOIN "
//                   + "(SELECT IM.MEMBER_ID, COUNT(*) AS ORDER_COUNT "
//                   + "FROM ORDERS O, MEMBER IM "
//                   + "WHERE O.MEMBER_ID = IM.MEMBER_ID "
//                   + "GROUP BY O.MEMBER_ID ) I "
//                   + "ON M.MEMBER_ID = I.MEMBER_ID";
//
//        List<Object[]> result = em.createNativeQuery(sql, "memberWithOrderCount")
//                .getResultList();
//
//        result.stream().forEach(row -> {
//            System.out.println("회원이름 : " + row[2] + ", 회원나이 : " + row[1]);
//        });
//    }
//
//    public static void namedNaNativeQuery(EntityManager em){
//        List<Member> result = em.createNamedQuery("Member.memberSQL", Member.class)
//                .setParameter("age", 20)
//                .getResultList();
//
//        result.stream().forEach(row -> {
//            System.out.println("회원이름 : " + row.getUsername() + ", 회원나이 : " + row.getAge());
//        });
//    }
}
