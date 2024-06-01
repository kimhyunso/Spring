package com.example.demo.repository.querydsl;

import com.example.demo.domain.*;
import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.QBoardDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BoardRepositoryImpl implements BoardRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Page<BoardDTO> getSearchPages(Pageable pageable, String searchValue){

        QBoard board = new QBoard("board");

        QAttachFile attachFile = QAttachFile.attachFile;
        QComment comment = QComment.comment;

        QMember member = new QMember("member");
        QCategory category = new QCategory("category");

//
//        Member member1 = queryFactory.selectFrom(member).where(member.memberId.eq(1L)).fetchOne();
//        Category category1 = queryFactory.selectFrom(category).where(category.cateId.eq(42L)).fetchOne();



        List<BoardDTO> content = queryFactory
                .select(new QBoardDTO(
                        board.boardId,
                        board.isNotice,
                        board.title,
                        board.content,
                        board.contentHTML,
                        board.isSecret,
                        board.writer,
                        board.modifyer,
                        category,
                        member
                ))
                .from(board)
                .leftJoin(board.attachFiles, attachFile).fetchJoin()
                .leftJoin(board.comments, comment).fetchJoin()
                .where(board.title.contains(searchValue).or(board.content.contains(searchValue)).or(board.contentHTML.contains(searchValue)).or(board.writer.contains(searchValue)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(board.count())
                .from(board)
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }



}
