package com.example.demo.dto;

import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class AttachFileDTO {

    private Long fileId;
    private String fileName;
    private Integer fileSize;
    private String orgName;
    private Integer downCnt;
    private Board board;
    private Member member;


    @QueryProjection
    public AttachFileDTO(Long fileId, String fileName, Integer fileSize, String orgName,
                         Integer downCnt, Board board, Member member){

        this.fileId = fileId;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.orgName = orgName;
        this.downCnt = downCnt;
        this.board = board;
        this.member = member;
    }



}
