package com.a5ur4.goldengains.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.Posts.CreatePostDTO;
import com.a5ur4.goldengains.dtos.Posts.PostDTO;
import com.a5ur4.goldengains.dtos.Posts.UpdatePostDTO;
import com.a5ur4.goldengains.entity.Categories;
import com.a5ur4.goldengains.entity.Posts;
import com.a5ur4.goldengains.entity.User;
import com.a5ur4.goldengains.repository.CategoriesRepository;
import com.a5ur4.goldengains.repository.PostsRepository;
import com.a5ur4.goldengains.repository.UserRepository;

@Service
public class PostsService {
    
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final CategoriesRepository categoriesRepository;

    public PostsService(PostsRepository postsRepository, UserRepository userRepository, CategoriesRepository categoriesRepository) {
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public String postPost(CreatePostDTO postDTO) {
        if (postsRepository.findByTitle(postDTO.title()).isPresent()) {
            return "Post already exists";
        } else {
            Posts newPost = new Posts();
            newPost.setTitle(postDTO.title());
            newPost.setContent(postDTO.content());
            newPost.setImage(postDTO.image());

            User userEntity = userRepository.findById(postDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + postDTO.user()));
            newPost.setUser(userEntity);

            Categories categoryEntity = categoriesRepository.findById(postDTO.category())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + postDTO.category()));
            newPost.setCategory(categoryEntity);

            postsRepository.save(newPost);
            return "Post created successfully";
        }
    }

    public List<PostDTO> getAllPosts() {
        return postsRepository.findAll()
                .stream()
                .map(this::convertToPostDTO)
                .toList();
    }

    public PostDTO findById(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));

        return convertToPostDTO(post);
    }

    public PostDTO updatePost(Long id, UpdatePostDTO updatedPost) {
        Posts existingPost = postsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));

        // If the field is null, it won't be updated
        if (updatedPost.title() != null) {
            existingPost.setTitle(updatedPost.title());
        }
        if (updatedPost.content() != null) {
            existingPost.setContent(updatedPost.content());
        }
        if (updatedPost.image() != null) {
            existingPost.setImage(updatedPost.image());
        }
        if (updatedPost.user() != null) {
            User user = userRepository.findById(updatedPost.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + updatedPost.user()));
            existingPost.setUser(user);
        }
        if (updatedPost.category() != null) {
            Categories category = categoriesRepository.findById(updatedPost.category())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + updatedPost.category()));
            existingPost.setCategory(category);
        }
        if (updatedPost.upvotes() != null) {
            existingPost.setUpvotes(updatedPost.upvotes());
        }
        if (updatedPost.downvotes() != null) {
            existingPost.setDownvotes(updatedPost.downvotes());
        }

        postsRepository.save(existingPost);
        return convertToPostDTO(existingPost);
    }

    public void deletePost(Long id) {
        Posts existingPost = postsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));

        postsRepository.delete(existingPost);
    }

    private PostDTO convertToPostDTO(Posts post) {
        return new PostDTO(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getImage(),
            post.getCreated_at(),
            post.getUpvotes(),
            post.getDownvotes(),
            post.getUser().getId(),
            post.getCategory().getId()
        );
    }
}
