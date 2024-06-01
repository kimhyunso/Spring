package com.example.chapter11.contoller;

import com.example.chapter11.domain.Member;
import com.example.chapter11.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping("/api/createMember")
    public ResponseEntity<Long> createMember(@RequestBody Member member){
        Long memberId = memberService.join(member);
        return ResponseEntity.ok().body(memberId);
    }


}
