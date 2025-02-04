package com.example.aop.controller;

import com.example.aop.dto.RequestBook;
import com.example.aop.service.AOPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AOPController {
    private final AOPService aopService;

    @GetMapping("/book/{bookName}")
    public ResponseEntity<Integer> readBookPrice(@PathVariable(name = "bookName") String bookName) {
        return ResponseEntity.status(HttpStatus.OK).body(aopService.readData(bookName));
    }

    @PatchMapping("/book")
    public ResponseEntity<Integer> insertBook(@RequestBody RequestBook requestBook) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aopService.createDate(requestBook));
    }

    @DeleteMapping("/book/{bookName}")
    public ResponseEntity<Integer> deleteBook(@PathVariable(name = "bookName") String bookName) {
        return ResponseEntity.status(HttpStatus.OK).body(aopService.deleteData(bookName));
    }

    @PutMapping("/book")
    public ResponseEntity<Integer> updateBook(@RequestBody RequestBook requestBook) {
        return ResponseEntity.status(HttpStatus.OK).body(aopService.modifyData(requestBook));
    }
}
