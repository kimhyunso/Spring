package com.example.demo.domain;

import com.example.demo.dto.LogDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@SuppressWarnings("Lombok")
@Setter
@Getter
@Table(name = "log")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logId")
    private Long logId;

    @Column(name = "content")
    private String content;

    @Column(name = "ip")
    private String ip;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "createAt")
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "modifyAt")
    @UpdateTimestamp
    private LocalDateTime modifyAt = LocalDateTime.now();


    public void convertToDomain(LogDTO dto){
        this.setLogId(dto.getLogId());
        this.setContent(dto.getContent());
        this.setUserEmail(dto.getUserEmail());
        this.setIp(dto.getIp());
    }



}
