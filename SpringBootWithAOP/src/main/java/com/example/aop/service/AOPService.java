package com.example.aop.service;

import com.example.aop.dto.RequestBook;
import com.example.aop.repository.AOPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AOPService {
    private final AOPRepository repository;

    public int createDate(RequestBook requestBook) {
        return repository.insertMap(requestBook.getName(), requestBook.getPrice());
    }

    public int modifyData(RequestBook requestBook) {
        return repository.updateMap(requestBook.getName(), requestBook.getPrice());
    }

    public int readData(String key) {
        return repository.findMap(key);
    }

    public int deleteData(String key) {
        return repository.removeMap(key);
    }
}
