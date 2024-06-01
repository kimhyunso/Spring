package com.example.demo.repository;

import com.example.demo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {


    // JPQL
    @Query(nativeQuery = true, value = "SELECT * FROM category WHERE is_upper NOT IN (:idx)")
    List<Category> findByIsUpperNotIn(long idx);






    // QueryDSL


}
