//package com.project.hyunso.prevBackup;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Optional;
//
//public class MemberService {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//
//    public void test(){
//        memberRepository.save(new Member(1L, "A"));
//
//
//        Optional<Member> member = memberRepository.findById(1L);
//        List<Member> allMembers = memberRepository.findAll();
//
//        memberRepository.deleteById(1L);
//    }
//
//
//}
