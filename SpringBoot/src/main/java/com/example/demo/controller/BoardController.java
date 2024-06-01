package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cate")
public class BoardController {

    private CategoryService categoryService;

    @Autowired
    public BoardController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{cateId}/boards")
    public String boards(@PathVariable long cateId, Model model){
        // TODO: AOP ==> categoryDTOList
        List<CategoryDTO> categoryDTOList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryDTOList);
        return "board/list";
    }




}
