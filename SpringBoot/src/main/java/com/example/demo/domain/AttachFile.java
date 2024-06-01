package com.example.demo.domain;

import com.example.demo.dto.AttachFileDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@SuppressWarnings("Lombok")
@Builder
@Table(name = "attachFile")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AttachFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileId")
    private Long fileId;

    @Column(name = "fileName")
    private String fileName;
    @Column(name = "fileSize")
    private Integer fileSize;
    @Column(name = "orgName")
    private String orgName;

    // 생성되는 시점에서 발동됨
    // @ColumnDefault("0")

    @Column(name = "downCnt")
    private Integer downCnt;

    @CreationTimestamp
    @Column(name = "createAt")
    private LocalDateTime createAt = LocalDateTime.now();
    @UpdateTimestamp
    @Column(name = "modifyAt")
    private LocalDateTime modifyAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    public void convertToDomain(AttachFileDTO dto){
        this.setFileId(dto.getFileId());
        this.setFileName(dto.getFileName());
        this.setOrgName(dto.getOrgName());
        this.setFileSize(dto.getFileSize());
        this.setDownCnt(dto.getDownCnt() == null ? 0 : dto.getDownCnt());
        this.setBoard(dto.getBoard());
        this.setMember(dto.getMember());
    }

    public void setMember(Member member){
        if (this.member != null){
            member.getAttachFiles().remove(this);
        }
        member.getAttachFiles().add(this);
        this.member = member;
    }

    public void setBoard(Board board){
        if (this.board != null){
            board.getAttachFiles().remove(this);
        }
        board.getAttachFiles().add(this);
        this.board = board;
    }

}
