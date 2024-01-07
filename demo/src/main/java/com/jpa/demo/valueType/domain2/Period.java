package com.jpa.demo.valueType.domain2;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
@Embeddable
@Data
public class Period {

    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Period(){
        this.startDate = new Date();
        this.endDate = new Date();
    }


    public boolean isWork(Date date){
        // 값 타입을 위한 메소를 정의할 수 있다.
        return true;
    }

}
