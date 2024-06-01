package com.example.chapter12.contoller;

import com.example.chapter12.domain.item.Book;
import com.example.chapter12.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


// 준영속
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(){
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(Book item){
        itemService.saveItem(item);
        return "redirect:/items";
    }







}
