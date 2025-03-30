package com.a5ur4.goldengains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.a5ur4.goldengains.dtos.CategoriesDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/get_by_id")
    public ResponseEntity<?> getCategoryById(@RequestParam Long id) {
        try {
            Categories category = categoriesService.findById(id);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Categories category) {
        try {
            if (category.getDescription() == null || category.getDescription().isBlank()) {
                return ResponseEntity.badRequest().body("Description cannot be null or empty");
            }
            String response = categoriesService.postCategory(category.getName(), category.getDescription());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody Categories updatedCategory) {
        try {
            if (updatedCategory.getDescription() == null || updatedCategory.getDescription().isBlank()) {
                return ResponseEntity.badRequest().body("Description cannot be null or empty");
            }
            CategoriesDTO categoryDTO = categoriesService.updateCategory(categoryId, updatedCategory);
            return ResponseEntity.ok(categoryDTO);
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
