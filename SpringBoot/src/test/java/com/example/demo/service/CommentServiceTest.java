package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.domain.Comment;
import com.example.demo.domain.Member;
import com.example.demo.dto.CommentDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Test
    public void getCommentListTest(){
        assertThat(commentService.getCommentList());
    }

    @Test
    public void saveTest(){

        Board board = boardService.findOne(10L).get();
        Member member = memberService.findOne(1L).get();

        IntStream.rangeClosed(1, 10).forEach(i->{
            CommentDTO commentDTO = CommentDTO.builder()
                    .commentId(Long.valueOf(i))
                    .writer("작성자" + i)
                    .content("댓글내용" + i)
                    .board(board)
                    .member(member)
                    .parentId(0)
                    .build();

            commentService.save(commentDTO);
        });
    }

    @Test
    public void update(){

        Board board = boardService.findOne(10L).get();
        Member member = memberService.findOne(1L).get();

        CommentDTO commentDTO = CommentDTO.builder()
                .commentId(10L)
                .content("업데이트내용")
                .parentId(0)
                .writer("업데이트작성자")
                .board(board)
                .modifyer("수정자")
                .member(member)
                .build();

        commentService.update(commentDTO);


    }

    @Test
    public void delete(){
        commentService.delete(1L);
    }



}
