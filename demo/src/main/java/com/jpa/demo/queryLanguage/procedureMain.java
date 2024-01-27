package com.jpa.demo.queryLanguage;

import com.jpa.demo.queryLanguage.domain1.Member;

import javax.persistence.*;

public class procedureMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction ex = em.getTransaction();

        try{
            ex.begin();
            storedProcedure(em);
            ex.commit();
        }catch (Exception e){
            System.out.println("Error : " + e.getMessage());
            ex.rollback();
        }
    }

    public static void storedProcedure(EntityManager em){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("proc_multiply");
        spq.registerStoredProcedureParameter("inParam", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("outParam", Integer.class, ParameterMode.OUT);

        spq.setParameter("inParam", 100);
        spq.execute();

        Integer out = (Integer) spq.getOutputParameterValue("outParam");
        System.out.println("out : " + out);
    }

    /**
     * 벌크 연산 : 여러 건을 한 번에 수정하거나, 삭제할 수 있는 기능
     * String sql = "UPDATE Product p SET p.price = p.price * 1.1 WHERE p.stockAmount < :stockAmount"
     * int resultCount = em.createQuery(sql)
     *               .setParameter("stockAmount", 10)
     *               .executeUpdate();
     *
     * String sql = "DELETE FROM Product p WHERE p.price < :price"
     * int resultCount = em.createQuery(sql)
     *              .setParameter("price", 10000)
     *              .executeUpdate();
     *
     * em.find(Member.class, 1L); // 영속성 컨텍스트에 등록됨 
     * String sql = "SELECT m FROM Member m";
     * List<Member> members = em.createQuery(sql, Member.class).getResultList(); // 버림
     * 영속성 컨텍스트 안에서 식별자로 확인 후 버리게된다. ==> em.find 리턴
     */




}
