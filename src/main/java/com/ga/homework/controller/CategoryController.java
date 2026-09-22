package com.ga.homework.controller;


import com.ga.homework.model.Category;
import com.ga.homework.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;


    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject){
        System.out.println("Calling createCategory ==> ");
        return categoryService.createCategory(categoryObject);
    }


    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("calling getCategories() ==> ");
        return categoryService.getCategories();
    }

    @GetMapping(path = "/categories/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory ==>");
        return categoryService.getCategory(categoryId);
    }


}
