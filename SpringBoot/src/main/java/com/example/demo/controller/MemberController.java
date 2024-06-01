package com.example.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member/")
@Log4j2
@Controller
public class MemberController {
    @GetMapping("/all")
    public void exAll(){
        log.info("exAll.................");
    }

    @GetMapping("/member")
    public void exMember(){
        log.info("exMember.................");
    }

    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin.................");
    }

    @GetMapping("/login")
    public String memberLogin(){
        return "member/login";
    }


    @GetMapping("/join")
    public String memberJoin(){
        return "member/join";
    }


}
