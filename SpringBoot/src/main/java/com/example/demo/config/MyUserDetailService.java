package com.example.demo.config;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


//@Component
//public class MyUserDetailService implements UserDetailsService {
//
//    private MemberService memberService;
//
//    @Autowired
//    public MyUserDetailService(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
////        Optional<Member> findOne = memberService.findOne(username);
////        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
////
////        return User.builder()
////                .username(member.getEmail())
////                .password(member.getPassword())
////                .roles(String.valueOf(member.getRoleSet()))
////                .build();
//        return null;
//    }
//}