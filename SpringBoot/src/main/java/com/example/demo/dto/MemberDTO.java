package com.example.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;



@Setter
@Getter
@Builder
public class MemberDTO {

    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String phoneNum;
    private String ip;

    @QueryProjection
    public MemberDTO(Long memberId, String email, String password, String name, String phoneNum, String ip){
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
        this.ip = ip;
    }


}
