//package com.project.hyunso.prevBackup;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//// 비지니스 계층
//@Service
//public class TestService {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    public List<MemberDTO> getMembersAll(){
//        memberRepository.save(new Member(1L, "회원A"));
//        memberRepository.save(new Member(2L, "회원B"));
//        memberRepository.save(new Member(3L, "회원C"));
//
//        return memberRepository.findAll().stream().map(member -> {
//            return MemberDTO.builder()
//                    .id(member.getId())
//                    .name(member.getName())
//                    .build();
//        }).collect(Collectors.toList());
//    }
//
//
//
//}
