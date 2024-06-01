//package com.project.hyunso.prevBackup;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//// 프레젠테이션 계층
//@RestController
//public class TestController {
//
//    @Autowired
//    private TestService testService;
//
//    // (라우터)
//    @GetMapping("/test")
//    public List<MemberDTO> test(){
//        List<MemberDTO> members = testService.getMembersAll();
//        return members;
//    }
//
//    @GetMapping("/test2")
//    public String test2(){
//        return "Hello Spring!";
//    }
//}
