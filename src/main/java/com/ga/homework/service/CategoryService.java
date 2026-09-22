package com.ga.homework.service;

import com.ga.homework.exception.InformationExistException;
import com.ga.homework.exception.InformationNotFoundException;
import com.ga.homework.model.Category;
import com.ga.homework.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category createCategory(Category categoryObject){
        System.out.println("Service Calling createCategory ==> ");

        Category category = categoryRepository.findByName(categoryObject.getName());
        if(category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }

    public List<Category> getCategories() {
        System.out.println("Service calling getCategories ==>");
        return categoryRepository.findAll();
    }

    public Category getCategory(Long categoryId) {
        System.out.println("service getCategory ==>");

        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "category with id " + categoryId + " not found"
                        )
                );
    }

}