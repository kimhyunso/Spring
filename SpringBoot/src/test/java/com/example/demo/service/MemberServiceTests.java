
package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;


@Transactional
@SpringBootTest
public class MemberServiceTests {

//    @Autowired
//    private PasswordEncoder encoder;

    @Autowired
    private MemberService memberService;
    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 10).forEach(i->{
            MemberDTO memberDTO = MemberDTO.builder()
                    .memberId(Long.valueOf(i))
                    .email("user" + i + "@naver.com")
                    .name("사용자" + i)
                    .password("1111")
                    .ip("192.168.0.5")
                    .phoneNum("01012345678")
                    .build();

            memberService.save(memberDTO);
        });
    }

}

