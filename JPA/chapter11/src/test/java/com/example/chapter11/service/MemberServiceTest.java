package com.example.chapter11.service;

import com.example.chapter11.domain.Address;
import com.example.chapter11.domain.Member;
import com.example.chapter11.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;


    @DisplayName("회원가입 : join")
    @Test
    public void 회원가입() throws Exception{
        // given
        Address address = new Address("서울시", "구로구", "202-1");

        Member member = new Member();
        member.setName("qweq");
        member.setAddress(address);

        // when
        Long memberId = memberService.join(member);

        // then
        assertEquals(member.getId(), memberService.findOne(memberId).getId());
    }

    @DisplayName("중복 회원 예외 : duplicate exception")
    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        // when
        memberService.join(member1);
        memberService.join(member2);


        // then
        fail("예외가 발생해야 한다.");
    }








}