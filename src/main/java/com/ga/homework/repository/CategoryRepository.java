package com.ga.homework.repository;

import com.ga.homework.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String categoryName);
    Category findByUserIdAndName(Long userId, String categoryName);
    List<Category> findByUserId(Long userId);

}
