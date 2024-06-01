package com.example.demo.dto;

import com.example.demo.domain.Log;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogDTO {
    private Long logId;
    private String content;
    private String userEmail;
    private String ip;


    @QueryProjection
    public LogDTO(Long logId, String content, String userEmail, String ip){
        this.logId = logId;
        this.content = content;
        this.userEmail = userEmail;
        this.ip = ip;
    }




}
