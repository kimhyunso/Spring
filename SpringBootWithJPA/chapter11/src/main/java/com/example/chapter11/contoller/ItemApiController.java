package com.example.chapter11.contoller;

import com.example.chapter11.domain.item.Book;
import com.example.chapter11.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;


    @PostMapping("/api/items/new")
    public ResponseEntity createItem(@RequestBody Book item){
        itemService.saveItem(item);

        return ResponseEntity.ok().build();
    }

}
