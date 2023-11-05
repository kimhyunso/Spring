package com.jpa.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
// 유니크 제약조건을 만들어줌
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    // 회원 이름은 필수로 입력, 10자를 초과하면 안됨
    @Column(name = "NAME", nullable = false, length = 10)
    private String name;
    
    // 컬럼정보가 없는 필드
    private Integer age;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // CLOB, BLOB 타입 매핑가능
    @Lob
    private String description;



}
