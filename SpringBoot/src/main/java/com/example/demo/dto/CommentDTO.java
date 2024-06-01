package com.example.demo.dto;


import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CommentDTO {

    private Long commentId;
    private Integer parentId;
    private String content;
    private String writer;
    private String modifyer;
    private Board board;
    private Member member;


    @QueryProjection
    public CommentDTO(Long commentId, Integer parentId, String content, String writer, String modifyer, Board board, Member member){
        this.commentId = commentId;
        this.parentId = parentId;
        this.content = content;
        this.writer = writer;
        this.modifyer = modifyer;
        this.board = board;
        this.member = member;
    }

}
