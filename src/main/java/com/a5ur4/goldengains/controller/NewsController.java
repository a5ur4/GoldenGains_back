package com.a5ur4.goldengains.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a5ur4.goldengains.dtos.News.NewsDTO;
import com.a5ur4.goldengains.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {
    
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllNews() {
        try {
            return ResponseEntity.ok(newsService.getAllNews());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(newsService.getNewsById(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNews(@RequestBody NewsDTO newsDTO) {
        try {
            if (newsDTO.title() == null || newsDTO.title().isBlank() ||
                    newsDTO.content() == null || newsDTO.content().isBlank()) {
                return ResponseEntity.badRequest().body("Title and content cannot be null or empty");
            }
            String response = newsService.postNews(newsDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    @PutMapping("/update/{newsId}")
    public ResponseEntity<?> updateNews(@PathVariable Long newsId, @RequestBody NewsDTO newsDTO) {
        try {
            NewsDTO updateNews = newsService.updateNewsDTO(newsId, newsDTO);
            return ResponseEntity.ok(updateNews);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity<?> deleteNews(@PathVariable Long newsId) {
        try {
            newsService.deleteNews(newsId);
            return ResponseEntity.ok("News deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
