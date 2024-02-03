package com.example.chapter11.repository;

import com.jpa.demo.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        String sql = "SELECT m FROM Member m";
        return em.createQuery(sql, Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        String sql = "SELECT m FROM Member m WHERE m.name = :name";

        return em.createQuery(sql, Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void deleteById(Long id){
        Member member = em.find(Member.class, id);
        em.remove(member);
    }

}
