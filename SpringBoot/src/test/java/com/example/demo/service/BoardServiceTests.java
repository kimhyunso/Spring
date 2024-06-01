package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Member;
import com.example.demo.dto.BoardDTO;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;


@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MemberService memberService;

    @Test
    public void getBoardListTest(){


        Sort sort = Sort.by("boardId").descending();

        Pageable pageable = PageRequest.of(0, 100).withSort(sort);

        String searchValue = "내용";

        boardService.getBoardList(pageable, searchValue).stream().forEach(boardDTO -> {
            System.out.println(boardDTO.getTitle());
        });
        // assertNotNull(boardService.getBoardList());
    }

    @Test
    public void saveTest(){
        // 1. CategoryService, boardService findOne ==> category_Id
        // 2. Category, Board = new () ==> 인스턴스 직접던지기

        Category category = categoryService.findOne(41L).get();

        Member member = memberService.findOne(1L).get();

        IntStream.rangeClosed(1, 10).forEach(i->{
            BoardDTO boardDTO = BoardDTO.builder()
                    .boardId(Long.valueOf(i))
                    .writer("작성자" + i)
                    .content("내용" + i)
                    .contentHTML("<div>" + "내용" + i + "</div>")
                    .title("제목" + i)
                    .isSecret('0')
                    .isNotice('0')
                    .category(category)
                    .member(member)
                    .build();

            boardService.save(boardDTO);
        });
    }

    @Test
    public void updateTest(){

        Category category = categoryService.findOne(1L).get();
        Member member = memberService.findOne(1L).get();

        BoardDTO boardDTO = BoardDTO.builder()
                .boardId(25L)
                .title("업데이트 제목")
                .modifyer("업데이트 사용자")
                .contentHTML("<div>업데이트됨</div>")
                .writer("수정자")
                .isNotice('1')
                .isSecret('0')
                .member(member)
                .category(category)
                .content("본문내용 업데이트")
                .build();


        boardService.update(boardDTO);
    }

    @Test
    public void deleteTest(){
        boardService.delete(1L);
    }



}
