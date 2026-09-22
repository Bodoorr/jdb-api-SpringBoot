package com.ga.homework.repository;

import com.ga.homework.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String categoryName);

}
