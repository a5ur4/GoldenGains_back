package com.a5ur4.goldengains.controller;

import com.a5ur4.goldengains.dtos.Posts.CreatePostDTO;
import com.a5ur4.goldengains.dtos.Posts.PostDTO;
import com.a5ur4.goldengains.dtos.Posts.UpdatePostDTO;
import com.a5ur4.goldengains.service.PostsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllPosts() {
        try {
            return ResponseEntity.ok(postsService.getAllPosts());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<?> getPostById(@RequestParam Long id) {
        try {
            PostDTO post = postsService.findById(id);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody CreatePostDTO postDTO) {
        try {
            if (postDTO.title() == null || postDTO.title().isBlank() ||
                    postDTO.content() == null || postDTO.content().isBlank()) {
                return ResponseEntity.badRequest().body("Title and content cannot be null or empty");
            }
            String response = postsService.postPost(postDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody UpdatePostDTO postDTO) {
        try {
            PostDTO updatedPost = postsService.updatePost(postId, postDTO);
            return ResponseEntity.ok(updatedPost);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        try {
            postsService.deletePost(postId);
            return ResponseEntity.ok("Post deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
