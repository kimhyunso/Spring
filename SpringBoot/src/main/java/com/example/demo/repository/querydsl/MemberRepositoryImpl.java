package com.example.demo.repository.querydsl;


import com.example.demo.domain.QMember;
import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.QMemberDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom {


    @Autowired
    private JPAQueryFactory queryFactory;


    @Override
    public Page<MemberDTO> getSearchPages(Pageable pageable, String searchValue){

        QMember member = new QMember("member");


        List<MemberDTO> content = queryFactory
                        .select(new QMemberDTO(
                                member.memberId,
                                member.email,
                                member.password,
                                member.name,
                                member.phoneNum,
                                member.ip
                        ))
                        .from(member)
                        .where(member.name.contains(searchValue)
                                .or(member.phoneNum.contains(searchValue))
                                .or(member.ip.contains(searchValue))
                                .or(member.email.contains(searchValue)))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        Long count = queryFactory
                .select(member.count())
                .from(member)
                .where(member.name.contains(searchValue)
                        .or(member.phoneNum.contains(searchValue))
                        .or(member.ip.contains(searchValue))
                        .or(member.email.contains(searchValue)))
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }


}
