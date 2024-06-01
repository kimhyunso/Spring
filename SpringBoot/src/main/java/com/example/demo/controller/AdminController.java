package com.example.demo.controller;


import com.example.demo.domain.Log;
import com.example.demo.service.BoardService;
import com.example.demo.service.LogService;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


// 2가지 컨트롤러
// Controller ==> application.properties나 어딘가 설정에 정의되어있는 suffix ==> .html
// RestController ==>
// Restful => HATEOAS

@Slf4j
@Controller
// @RestController
@RequestMapping("/admin")
public class AdminController {


    private final LogService logService;

    private final MemberService memberService;

    private final BoardService boardService;

    @Autowired
    public AdminController(LogService logService, MemberService memberService, BoardService boardService){
        this.logService = logService;
        this.memberService = memberService;
        this.boardService = boardService;
    }

    // 쿼리스트링 : ?page=1&sort=descending

    @GetMapping("")
    public String getLogsPage(Model model, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC, size = 10) Pageable pageable,
                             @RequestParam(name = "searchValue", defaultValue = "") String searchValue){

        model.addAttribute("logList", logService.getLogList(searchValue, pageable));
        return "admin/index";
    }


    // TODO: member 나중에 만들기
    @GetMapping("/members")
    public String getMembersPage(Model model, @PageableDefault(size = 10, sort = "memberId", direction = Sort.Direction.DESC) Pageable pageable,
                                 @RequestParam(name = "searchValue", defaultValue = "") String searchValue){
        model.addAttribute("memberList", memberService.getMemberList(pageable, searchValue));
        return "admin/member/list";
    }

    @GetMapping("/boards")
    public String getBoardsPage(Model model, @PageableDefault(size = 10, sort = "memberId", direction = Sort.Direction.DESC) Pageable pageable,
                                 @RequestParam(name = "searchValue", defaultValue = "") String searchValue){
        model.addAttribute("boardList", boardService.getBoardList(pageable, searchValue));
        return "admin/board/list";
    }



    



}
