package com.jpa.demo.valueType.domain2;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Member {
    @Id @GeneratedValue
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
