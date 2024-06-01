package com.example.chapter14.domain;

import jakarta.persistence.*;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;

    // 순서가 있는 컬렉션으로 인식
    @OneToMany(mappedBy = "board")
    @OrderColumn(name = "POSITION")
    private List<Comment> comments = new ArrayList<>();


}
