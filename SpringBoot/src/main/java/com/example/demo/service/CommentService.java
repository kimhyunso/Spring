package com.example.demo.service;

import com.example.demo.domain.Comment;
import com.example.demo.dto.CommentDTO;
import com.example.demo.repository.CommentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommentService {



    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }



    @Transactional
    public List<CommentDTO> getCommentList(){
        return commentRepository.findAll().stream().map(comment -> {
            return CommentDTO.builder()
                    .commentId(comment.getCommentId())
                    .parentId(comment.getParentId())
                    .content(comment.getContent())
                    .modifyer(comment.getModifyer())
                    .writer(comment.getWriter())
                    .build();
        }).collect(Collectors.toList());
    }


    public Optional<Comment> findOne(Long commentId){
        return commentRepository.findById(commentId);
    }


    @Transactional
    public void update(CommentDTO commentDTO){

        findOne(commentDTO.getCommentId()).ifPresent(comment -> {
            comment.convertToDomain(commentDTO);
        });

    }


    @Transactional
    public void save(CommentDTO commentDTO){

        Comment comment = new Comment();
        comment.convertToDomain(commentDTO);

        commentRepository.save(comment);
    }


    @Transactional
    public void delete(Long commentId){
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public long count(){
        return commentRepository.count();
    }

}
