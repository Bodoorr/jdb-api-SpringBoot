package com.ga.homework.controller;


import com.ga.homework.model.Category;
import com.ga.homework.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path="/api")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;


    @PostMapping("/categories")
    public Category createCategory(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("image") MultipartFile image){
        System.out.println("Calling createCategory ==> ");
        return categoryService.createCategory(name,description,image);
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

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    @DeleteMapping("/categories/{categoryId}")
    public Category deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }


}
