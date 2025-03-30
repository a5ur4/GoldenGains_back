package com.a5ur4.goldengains.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.CategoriesDTO;
import com.a5ur4.goldengains.entity.Categories;
import com.a5ur4.goldengains.repository.CategoriesRepository;

@Service
public class CategoriesService {
    
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public String postCategory(String name, String description) {
        if (categoriesRepository.findByName(name).isPresent()) {
            return "Category already exists";
        } else {
            Categories newCategory = new Categories();
            newCategory.setName(name);
            newCategory.setDescription(description);
            categoriesRepository.save(newCategory);
            return "Category created successfully";
        }
    }

    public List<CategoriesDTO> getAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories.stream()
                .map(this::convertToCategoriesDTO)
                .toList();
    }

    public Categories findById(Long id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public CategoriesDTO updateCategory(Long id, Categories updatedCategory) {
        Categories existingCategory = categoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription()); // Update the description
        categoriesRepository.save(existingCategory);
        return convertToCategoriesDTO(existingCategory);
    }

    public void deleteCategory(Long id) {
        Categories existingCategory = categoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        categoriesRepository.delete(existingCategory);
    }

    private CategoriesDTO convertToCategoriesDTO(Categories category) {
        return new CategoriesDTO(
            category.getId(),
            category.getName(),
            category.getDescription(),
            category.getPost().stream()
                .map(post -> post.getId())
                .toList()
        );
    }
}
