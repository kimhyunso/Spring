package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.repository.querydsl.LogRepositoryImpl;
import com.example.demo.service.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class Config {

    private final MemberRepository memberRepository;

    private final CategoryRepository categoryRepository;

    private final BoardRepository boardRepository;

    private final LogRepository logRepository;

    private final AttachRepository attachRepository;

    private final CommentRepository commentRepository;


    @Autowired
    public Config(MemberRepository memberRepository, CategoryRepository categoryRepository,
                  BoardRepository boardRepository, LogRepository logRepository,
                  AttachRepository attachRepository, CommentRepository commentRepository){
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.boardRepository = boardRepository;
        this.logRepository = logRepository;
        this.attachRepository = attachRepository;
        this.commentRepository = commentRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }


    @Bean
    public CategoryService categoryService(){
        return new CategoryService(categoryRepository);
    }


    @Bean
    public BoardService boardService(){
        return new BoardService(boardRepository);
    }

    @Bean
    public LogService logService(){return new LogService(logRepository);}

    @Bean
    public AttachFileService attachFile(){return new AttachFileService(attachRepository);}

    @Bean
    public CommentService commentService(){return new CommentService(commentRepository);}

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer(){
        return p->{
            p.setOneIndexedParameters(true);
            p.setMaxPageSize(10);
        };
    }



}
