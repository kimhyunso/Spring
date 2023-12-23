package com.jpa.demo.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * 이것을 상속받는 모든 엔티티는 등록일과 수정일을 추가하게된다.
 *
 */

@MappedSuperclass
@Data
public class BaseEntity {

    private Date createdDate;
    private Date lastModitieDate;

}
