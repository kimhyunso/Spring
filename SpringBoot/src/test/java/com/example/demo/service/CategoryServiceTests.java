
package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.BoardService;
import com.example.demo.service.CategoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    // C
    @Test
    public void saveTest(){


        IntStream.rangeClosed(1, 10).forEach(i->{

            int min = 0;
            int max = 1;

            int randomInt = (int)Math.floor(Math.random() * (max - min + 1) + min);



            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .cateId(Long.valueOf(i))
                    .isShow((char)(randomInt + '0'))
                    .isDrop((char)(randomInt + '0'))
                    .content("테스트" + i)
                    .build();

            categoryService.save(categoryDTO);
        });
    }


    // R
    @Test
    public void getLists(){


        assertThat(categoryService.getCategoryList());
    }

    // U
    @Test
    public void updateTest(){

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .cateId(Long.valueOf(1))
                .content("업데이트")
                .isDrop('1')
                .isShow('1')
                .build();

        categoryService.update(categoryDTO);

    }
    // D
    @Test
    public void deleteTest(){

        IntStream.rangeClosed(2, 10).forEach(i->{

            categoryService.delete(Long.valueOf(i));
        });

    }


    @Test
    public void findOneTest(){
        Long idx = 1L;
        Optional<Category> category = categoryService.findOne(idx);
        String content = category.get().getContent();

        assertThat(content);
    }


}


