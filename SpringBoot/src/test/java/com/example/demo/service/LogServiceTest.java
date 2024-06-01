package com.example.demo.service;

import com.example.demo.dto.LogDTO;


import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService logService;



    @Test
    public void getLogListTest(){
        // Sort sort = Sort.by("log_id").descending();

        String searchValue = "로그";

        Pageable pageable =  PageRequest.of(0, 5, Sort.Direction.DESC, "log_id");
        logService.getLogList(searchValue, pageable);
    }

    @Test
    public void saveTest(){
        IntStream.rangeClosed(1, 10).forEach(i->{
            LogDTO logDTO = LogDTO.builder()
                    .logId(Long.valueOf(i))
                    .content("로그내용" + i)
                    .userEmail("유저이름" + i)
                    .ip("192.168.0." + i)
                    .build();

            logService.save(logDTO);
        });
    }

    @Test
    public void updateTest(){

        LogDTO logDTO = LogDTO.builder()
                .logId(Long.valueOf(12))
                .content("업데이트 로그")
                .userEmail("변경된 이메일")
                .ip("123.123.100.0")
                .build();

        logService.update(logDTO);
    }

    @Test
    public void deleteTest(){

        Long logId = 11L;
        logService.delete(logId);
    }

}
