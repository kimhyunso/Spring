package com.example.chapter09.domain.domain3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Embedded
    private Address address;

    @Embedded
    private PhoneNumber phoneNumber;

    // 집주소
//    @Embedded
//    private Address homeAddress;
    // 회사주소
//    @Embedded
//    private Address companyAddress;
    // 컬럼 중복 발생

//    @Embedded
//    @AttributeOverrides({
//        @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
//        @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
//        @AttributeOverride(name = "zipcode", column = @Column(name = "COMPANY_ZIPCODE")),
//        @AttributeOverride(name = "state", column = @Column(name = "COMPANY_STATE"))
//    })
//    private Address companyAddress;

}
