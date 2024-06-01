
package com.example.demo.security;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import groovy.transform.ASTTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PasswordTest {

//    private final MemberRepository repository;
//
//    private final PasswordEncoder encoder;
//
//    @Autowired
//    public PasswordTest(PasswordEncoder encoder, MemberRepository repository){
//        this.encoder = encoder;
//        this.repository = repository;
//    }
//
//    @Test
//    public void memberSelectTest(){
//        List<Member> memberList =  repository.findAll();
//        System.out.println(memberList);
//    }




}

