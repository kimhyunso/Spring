package com.example.demo.service;

import com.example.demo.domain.AttachFile;
import com.example.demo.dto.AttachFileDTO;
import com.example.demo.repository.AttachRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AttachFileService {

    private final AttachRepository attachRepository;

    public AttachFileService(AttachRepository attachRepository){
        this.attachRepository = attachRepository;
    }


    @Transactional
    public List<AttachFileDTO> getAttachFileList(){
        return attachRepository.findAll().stream()
                .map(attachFile -> {
                    return AttachFileDTO.builder()
                            .fileId(attachFile.getFileId())
                            .fileName(attachFile.getFileName())
                            .fileSize(attachFile.getFileSize())
                            .orgName(attachFile.getOrgName())
                            .downCnt(attachFile.getDownCnt())
                            .board(attachFile.getBoard())
                            .build();
                }).collect(Collectors.toList());
    }


    @Transactional
    public Optional<AttachFile> findOne(Long attachId){
        return attachRepository.findById(attachId);
    }

    @Transactional
    public void save(AttachFileDTO attachFileDTO){
        AttachFile attachFile = new AttachFile();
        attachFile.convertToDomain(attachFileDTO);
        attachRepository.save(attachFile);
    }


    @Transactional
    public void update(AttachFileDTO attachFileDTO){
        findOne(attachFileDTO.getFileId()).ifPresent(attachFile -> {
            attachFile.convertToDomain(attachFileDTO);
        });
    }

    @Transactional
    public void delete(Long attachId){
        attachRepository.deleteById(attachId);
    }


    @Transactional
    public long count(){
        return attachRepository.count();
    }

}
