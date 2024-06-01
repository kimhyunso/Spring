package com.example.demo.repository.querydsl;

import com.example.demo.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

    Page<MemberDTO> getSearchPages(Pageable pageable, String searchValue);

}
