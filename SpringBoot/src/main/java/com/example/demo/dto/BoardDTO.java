package com.example.demo.dto;

import com.example.demo.domain.AttachFile;
import com.example.demo.domain.Category;
import com.example.demo.domain.Member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;


@Builder
@Getter
@Setter
public class BoardDTO {

    private Long boardId;
    private char isNotice;
    private String title;
    private String content;
    private String contentHTML;
    private char isSecret;
    private String writer;
    private String modifyer;
    private Category category;
    private Member member;

    @QueryProjection
    public BoardDTO(Long boardId, char isNotice, String title, String content, String contentHTML,
                    char isSecret, String writer, String modifyer, Category category, Member member){
        this.boardId = boardId;
        this.isNotice = isNotice;
        this.title = title;
        this.content = content;
        this.contentHTML = contentHTML;
        this.isSecret = isSecret;
        this.writer = writer;
        this.modifyer = modifyer;
        this.category = category;
        this.member = member;
    }





}
