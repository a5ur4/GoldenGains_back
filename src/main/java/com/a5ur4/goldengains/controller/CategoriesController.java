package com.a5ur4.goldengains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.a5ur4.goldengains.dtos.Categories.CategoriesDTO;
import com.a5ur4.goldengains.dtos.Categories.CreateCategoriesDTO;
import com.a5ur4.goldengains.dtos.Categories.UpdateCategoriesDTO;
import com.a5ur4.goldengains.entity.Categories;
import com.a5ur4.goldengains.service.CategoriesService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllCategories() {
        try {
            return ResponseEntity.ok(categoriesService.getAllCategories());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            Categories category = categoriesService.findById(id);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoriesDTO categoryDTO) {
        try {
            if (categoryDTO.name() == null || categoryDTO.name().isBlank() ||
                    categoryDTO.description() == null || categoryDTO.description().isBlank()) {
                return ResponseEntity.badRequest().body("Name and description cannot be null or empty");
            }
            String response = categoriesService.postCategory(categoryDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody UpdateCategoriesDTO categoryDTO) {
        try {
            CategoriesDTO updatedCategory = categoriesService.updateCategory(categoryId, categoryDTO);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoriesService.deleteCategory(categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
