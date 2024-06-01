package com.example.demo.domain;


import com.example.demo.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("Lombok")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardId")
    private Long boardId;

    @Column(name = "isNotice")
    private char isNotice;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    @Lob
    private String content;

    @Column(name = "contentHTML")
    @Lob
    private String contentHTML;

    @Column(name = "isSecret")
    private char isSecret;

    @Column(name = "writer")
    private String writer;

    @Column(name = "createAt")
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "modifyer")
    private String modifyer;

    @Column(name = "modifyAt")
    @UpdateTimestamp
    private LocalDateTime modifyAt = LocalDateTime.now();

    // 연관관계의 주인
    @ManyToOne
    @JoinColumn(name = "cateId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<AttachFile> attachFiles = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();


    public void convertToDomain(BoardDTO dto){

        this.setIsNotice(dto.getIsNotice());
        this.setTitle(dto.getTitle());
        this.setContent(dto.getContent());
        this.setContentHTML(dto.getContentHTML());
        this.setWriter(dto.getWriter());
        this.setIsSecret(dto.getIsSecret());
        this.setModifyer(dto.getModifyer());
        this.setCategory(dto.getCategory());
        this.setMember(dto.getMember());
    }

    public void setCategory(Category category){
        if (this.category != null){
            category.getBoards().remove(this);
        }
        category.getBoards().add(this);
        this.category = category;
    }

    public void setMember(Member member){
        if (this.member != null){
            member.getBoards().remove(this);
        }
        member.getBoards().add(this);
        this.member = member;
    }


}
