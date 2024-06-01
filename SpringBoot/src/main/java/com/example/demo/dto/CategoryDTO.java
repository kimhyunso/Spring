package com.example.demo.dto;

import com.example.demo.domain.Board;
import com.example.demo.domain.Category;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Builder
public class CategoryDTO {

    private Long cateId;
    private String content;
    private char isShow;
    private char isDrop;



    @QueryProjection
    public CategoryDTO(Long cateId, String content, char isShow, char isDrop){
        this.cateId = cateId;
        this.content = content;
        this.isShow = isShow;
        this.isDrop = isDrop;

    }


}
