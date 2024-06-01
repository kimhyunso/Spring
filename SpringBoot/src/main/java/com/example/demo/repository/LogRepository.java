package com.example.demo.repository;

import com.example.demo.domain.Log;
import com.example.demo.dto.LogDTO;
import com.example.demo.repository.querydsl.LogRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long>, LogRepositoryCustom {

}
