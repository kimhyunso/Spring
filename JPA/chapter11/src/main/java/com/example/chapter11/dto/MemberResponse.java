package com.example.chapter11.dto;

import com.example.chapter11.domain.Address;
import com.example.chapter11.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponse extends BaseEntity {
    private Long id;
    private String name;
    private Address address;

    public MemberResponse(){
        setCreatedDate(new Date());
    }

}
