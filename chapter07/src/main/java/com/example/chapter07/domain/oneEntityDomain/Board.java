package com.example.chapter07.domain.oneEntityDomain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "BOARD")
//@SecondaryTable(name = "BOARD_DETAIL",
//    pkJoinColumns = @PrimaryKeyJoinColumn(name = "BOARD_DETAIL_ID"))
public class Board {


    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    private String title;

    @Column(name = "BOARD_DETAIL")
    private String content;
}
