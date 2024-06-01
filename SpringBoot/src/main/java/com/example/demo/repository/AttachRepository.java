package com.example.demo.repository;

import com.example.demo.domain.AttachFile;
import com.example.demo.dto.AttachFileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttachRepository extends JpaRepository<AttachFile, Long> {




}
