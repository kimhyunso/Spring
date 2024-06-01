package com.example.demo.repository.querydsl;

import com.example.demo.dto.LogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogRepositoryCustom {

    Page<LogDTO> searchPage(String searchValue, Pageable pageable);

}
