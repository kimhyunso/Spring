package com.example.demo.service;

import com.example.demo.domain.Log;
import com.example.demo.dto.LogDTO;
import com.example.demo.repository.LogRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }


    @Transactional
    public Page<LogDTO> getLogList(String searchValue, Pageable pageable){
        return logRepository.searchPage(searchValue, pageable);
    }

    @Transactional
    public Optional<Log> findOne(Long logId){
        return logRepository.findById(logId);
    }

    @Transactional
    public void save(LogDTO logDTO){
       Log log = new Log();
       log.convertToDomain(logDTO);
       logRepository.save(log);
    }


    // update 할 때 Transactional을 처리하지 않으면 commit 하지 않음
    @Transactional
    public void update(LogDTO logDTO){
        findOne(logDTO.getLogId()).ifPresent(findLog->{
            findLog.convertToDomain(logDTO);
        });
    }

    @Transactional
    public void delete(Long logId){
        logRepository.deleteById(logId);
    }

    @Transactional
    public void count(){
        logRepository.count();
    }

}
