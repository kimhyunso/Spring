package com.example.chapter12.dto;

import com.example.chapter12.domain.Address;
import com.example.chapter12.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
