package com.example.demo.controller;



import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequestMapping("/")
@Controller
public class MainController {

    private CategoryService categoryService;

    @Autowired
    public MainController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping({"/", "list"})
    public String list(Model model){
        List<CategoryDTO> categoryDTOList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryDTOList);
        return "index";
    }

}
