package com.example.chapter12.repository;

import com.example.chapter12.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByEmailAndName(String email, String name);

    @Query("select m from Member m where m.username = ?1")
    Member findByUserName(String name);
}
