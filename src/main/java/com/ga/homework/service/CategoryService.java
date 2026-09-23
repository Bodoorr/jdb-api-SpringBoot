package com.ga.homework.service;

import com.ga.homework.exception.InformationExistException;
import com.ga.homework.exception.InformationNotFoundException;
import com.ga.homework.model.Category;
import com.ga.homework.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {
    private final String UPLOAD_DIR = "uploads/";
    private CategoryRepository categoryRepository;

    public Category createCategory(String name, String description, MultipartFile image) {
        System.out.println("Service Calling createCategory ==> ");

        Category category = categoryRepository.findByName(name);
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        }
        Category newCategory = new Category();

        newCategory.setName(name);
        newCategory.setDescription(description);
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = image.getOriginalFilename();

            String uniqueId = UUID.randomUUID().toString();

            String fileName = uniqueId + "_" + originalFileName;

            Path filePath = uploadPath.resolve(fileName);

            image.transferTo(filePath);

            newCategory.setImageUrl(UPLOAD_DIR + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Could not save image", e);
        }
        return categoryRepository.save(newCategory);
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

    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("service calling updateCategory ==>");

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "category with id " + categoryId + " not found"
                        )
                );

        existingCategory.setName(categoryObject.getName());
        existingCategory.setDescription(categoryObject.getDescription());

        return categoryRepository.save(existingCategory);
    }


    public Category deleteCategory(Long categoryId) {
        System.out.println("service calling deleteCategory ==>");

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "category with id " + categoryId + " not found"
                        )
                );

        categoryRepository.delete(category);
        return category;
    }


}