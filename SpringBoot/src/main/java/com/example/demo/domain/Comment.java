package com.example.demo.domain;

import com.example.demo.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@SuppressWarnings("Lombok")
@Table(name = "comment")
@Entity
@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long commentId;

    @Column(name = "parentId")
    private Integer parentId;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "modifyer")
    private String modifyer;

    @Column(name = "createAt")
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "modifyAt")
    @UpdateTimestamp
    private LocalDateTime modifyAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;


    public void setBoard(Board board) {
        if (this.board != null){
            board.getComments().remove(this);
        }
        board.getComments().add(this);
        this.board = board;
    }

    public void setMember(Member member) {
        if (this.member != null){
            member.getComments().remove(this);
        }
        member.getComments().add(this);
        this.member = member;
    }

    public void convertToDomain(CommentDTO dto){
        this.setCommentId(dto.getCommentId());
        this.setParentId(dto.getParentId());
        this.setContent(dto.getContent());
        this.setWriter(dto.getWriter());
        this.setBoard(dto.getBoard());
        this.setMember(dto.getMember());
        this.setModifyer(dto.getModifyer());
    }


}
