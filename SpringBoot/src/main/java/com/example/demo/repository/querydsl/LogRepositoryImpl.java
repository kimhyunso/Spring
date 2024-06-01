package com.example.demo.repository.querydsl;



import com.example.demo.domain.QLog;
import com.example.demo.dto.LogDTO;
import com.example.demo.dto.QLogDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class LogRepositoryImpl implements LogRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;



    @Override
    public Page<LogDTO> searchPage(String searchValue, Pageable pageable){

        QLog log = new QLog("log");

        List<LogDTO> content = queryFactory
                .select(new QLogDTO(
                        log.logId,
                        log.content,
                        log.userEmail,
                        log.ip
                ))
                .from(log)
                .where(log.content.contains(searchValue).or(log.ip.contains(searchValue)).or(log.userEmail.contains(searchValue)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long count = queryFactory
                .select(log.count())
                .from(log)
                .where(log.content.contains(searchValue).or(log.ip.contains(searchValue)).or(log.userEmail.contains(searchValue)))
                .fetchOne();

        return new PageImpl<>(content, pageable, count);

    }



}
