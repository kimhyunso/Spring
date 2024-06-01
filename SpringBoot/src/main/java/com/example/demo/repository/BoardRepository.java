package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.domain.Category;
import com.example.demo.repository.querydsl.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    List<Board> findAllByCategory(Category category);
}
