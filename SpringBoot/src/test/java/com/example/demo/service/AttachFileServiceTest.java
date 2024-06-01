package com.example.demo.service;

import com.example.demo.domain.AttachFile;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.dto.AttachFileDTO;
import com.example.demo.service.AttachFileService;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
public class AttachFileServiceTest {

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Test
    public void getAttachFileListTest(){
        assertNull(attachFileService.getAttachFileList());
    }

    @Test
    public void saveTest(){

        Board board = boardService.findOne(10L).get();
        Member member = memberService.findOne(1L).get();

        IntStream.rangeClosed(1, 10).forEach(i->{

            String uuid = UUID.randomUUID().toString();

            AttachFileDTO attachFileDTO = AttachFileDTO.builder()
                    .fileId(Long.valueOf(i))
                    .fileName(uuid + "card-" + i)
                    .fileSize(20 + i)
                    .orgName("card-" + i)
                    .member(member)
                    .board(board)
                    .build();

            attachFileService.save(attachFileDTO);
        });

    }

    @Test
    public void updateTest(){

        AttachFile attachFile = attachFileService.findOne(1L).get();
        String uuid = UUID.randomUUID().toString();

        AttachFileDTO attachFileDTO = AttachFileDTO.builder()
                .fileId(attachFile.getFileId())
                .fileName(uuid + "업데이트파일-" + 1)
                .fileSize(attachFile.getFileSize())
                .orgName("업데이트파일-" + 1)
                .member(attachFile.getMember())
                .board(attachFile.getBoard())
                .build();


        attachFileService.update(attachFileDTO);
    }

    @Test
    public void deleteTest(){

        attachFileService.delete(2L);

    }

    @Test
    public void countTest(){
        assertNull(attachFileService.count());
    }

}
