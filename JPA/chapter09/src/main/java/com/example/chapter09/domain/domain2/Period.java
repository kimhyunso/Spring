package com.example.chapter09.domain.domain2;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
