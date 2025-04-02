package com.a5ur4.goldengains.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.Categories.CategoriesDTO;
import com.a5ur4.goldengains.dtos.Categories.CreateCategoriesDTO;
import com.a5ur4.goldengains.dtos.Categories.UpdateCategoriesDTO;
import com.a5ur4.goldengains.entity.Categories;
import com.a5ur4.goldengains.repository.CategoriesRepository;
import com.a5ur4.goldengains.repository.PostsRepository;
import com.a5ur4.goldengains.entity.Posts;

@Service
public class CategoriesService {
    
    private final PostsRepository postsRepository;
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository, PostsRepository postsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.postsRepository = postsRepository;
    }

    public String postCategory(CreateCategoriesDTO categoriesDTO) {
        if (categoriesRepository.findByName(categoriesDTO.name()).isPresent()) {
            return "Category already exists";
        } else {
            Categories newCategory = new Categories();
            newCategory.setName(categoriesDTO.name());
            newCategory.setDescription(categoriesDTO.description());
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

    public CategoriesDTO updateCategory(Long id, UpdateCategoriesDTO updateCategoriesDTO) {
        Categories existingCategory = categoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        if (updateCategoriesDTO.name() != null) {
            existingCategory.setName(updateCategoriesDTO.name());
        }
        if (updateCategoriesDTO.description() != null) {
            existingCategory.setDescription(updateCategoriesDTO.description());
        }
        if (updateCategoriesDTO.posts() != null) {
            List<Posts> posts = postsRepository.findAllById(updateCategoriesDTO.posts());
            existingCategory.setPosts(posts);
        }
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
            category.getPosts().stream().map(Posts::getId).toList()
        );
    }
}
