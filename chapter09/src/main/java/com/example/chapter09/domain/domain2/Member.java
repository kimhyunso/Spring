package com.example.chapter09.domain.domain2;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    // 근무기간
//    @Temporal(TemporalType.DATE)
//    private Date startDate;
//    @Temporal(TemporalType.DATE)
//    private Date endDate;
//    // 집주소
//    private String city;
//    private String street;
//    private String zipcode;

    @Embedded
    private Period workPeriod;
    @Embedded
    private Address homeAddress;
}
