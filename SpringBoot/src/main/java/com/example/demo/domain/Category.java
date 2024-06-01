package com.example.demo.domain;

import com.example.demo.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Lombok")
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder(builderMethodName = "CategoryBuilder")
public class Category {

    // IDENTITY = mariadb
    // autoincrement 자동으로 증가를 해주면 IDENTITY MARIADB==
    // SEQUENCE = oracle, mariadb
    // sequence를 생성해서 증가하는 방식을 사용할 경우 사용하게 됩니다.
    // TABLE = DB
    // tableA, tableB만들어서 (자동으로 만들어줄 수 있게끔 설정) tableB에 의해서 tableA에 primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cateId")
    private Long cateId;

    @Column(name = "content")
    private String content;

    @Column(name = "isShow")
    private char isShow;

    @Column(name = "isDrop")
    private char isDrop;

    // 공부 필요!
    // 디자인 패턴 : 프록시
    // ============= 영속성 컨텍스트의 Lazy Loading =======


    // mappedBy 읽기전용
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Board> boards = new ArrayList<>();


    public void convertToDomain(CategoryDTO dto){
        this.setCateId(dto.getCateId());
        this.setContent(dto.getContent());
        this.setIsShow(dto.getIsShow());
        this.setIsDrop(dto.getIsDrop());
    }
}
