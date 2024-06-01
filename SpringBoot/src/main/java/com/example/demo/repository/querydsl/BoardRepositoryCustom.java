package com.example.demo.repository.querydsl;

import com.example.demo.domain.Board;
import com.example.demo.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BoardDTO> getSearchPages(Pageable pageable, String searchValue);


}
