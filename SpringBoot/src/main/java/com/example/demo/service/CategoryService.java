package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategoryList() {


        return categoryRepository.findAll().stream()
                .map(category -> {
                    return CategoryDTO.builder()
                            .cateId(category.getCateId())
                            .isDrop(category.getIsDrop())
                            .isShow(category.getIsShow())
                            .content(category.getContent())

                            .build();
                }).collect(Collectors.toList());
    }



    @Transactional
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.convertToDomain(categoryDTO);

        categoryRepository.save(category);
    }

    @Transactional
    public void update(CategoryDTO categoryDTO){
        findOne(categoryDTO.getCateId()).ifPresent(findCategory->{
            findCategory.convertToDomain(categoryDTO);
        });

    }


    @Transactional
    public void delete(Long category_id){
        categoryRepository.deleteById(category_id);
    }


    @Transactional
    public Optional<Category> findOne(Long idx){
        return categoryRepository.findById(idx).stream().findFirst();
    }

    @Transactional
    public long count(){
        return categoryRepository.count();
    }

}
