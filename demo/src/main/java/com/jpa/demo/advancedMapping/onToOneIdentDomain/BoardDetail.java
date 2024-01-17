package com.jpa.demo.advancedMapping.onToOneIdentDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class BoardDetail {

    @Id
    private Long boardId;
//    @Id
    @MapsId
    @OneToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    private String content;

}
