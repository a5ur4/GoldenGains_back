package com.a5ur4.goldengains.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.News.NewsDTO;
import com.a5ur4.goldengains.repository.CategoriesRepository;
import com.a5ur4.goldengains.repository.NewsRepository;
import com.a5ur4.goldengains.entity.Categories;
import com.a5ur4.goldengains.entity.News;

@Service
public class NewsService {
    
    private final NewsRepository newsRepository;
    private final CategoriesRepository categoriesRepository;

    public NewsService(NewsRepository newsRepository, CategoriesRepository categoriesRepository) {
        this.newsRepository = newsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public String postNews(NewsDTO newsDTO) {
        if (newsRepository.findByTitle(newsDTO.title()).isPresent()) {
            return "News already exists";
        }
        
        if (newsDTO.categories() == null || newsDTO.categories().isEmpty()) {
            throw new RuntimeException("At least one category must be provided");
        }

        List<Categories> categories = categoriesRepository.findAllById(newsDTO.categories());
        if (categories.isEmpty()) {
            throw new RuntimeException("No valid categories found with the provided IDs");
        }

        News newNews = new News();
        newNews.setTitle(newsDTO.title());
        newNews.setContent(newsDTO.content());
        newNews.setLink(newsDTO.link());
        newNews.setCategory(categories);
        
        newsRepository.save(newNews);
        return "News created successfully";
    }

    public NewsDTO getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id: " + id));
        return convertToNewsDTO(news);
    }

    public List<NewsDTO> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream()
                .map(this::convertToNewsDTO)
                .collect(Collectors.toList());
    }

    public List<NewsDTO> getNewsByCategory(Long categoryId) {
        return newsRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::convertToNewsDTO)
                .collect(Collectors.toList());
    }

    public NewsDTO updateNewsDTO(Long id, NewsDTO newsDTO) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id: " + id));
        
        if (newsDTO.title() != null) {
            existingNews.setTitle(newsDTO.title());
        }
        if (newsDTO.content() != null) {
            existingNews.setContent(newsDTO.content());
        }
        if (newsDTO.link() != null) {
            existingNews.setLink(newsDTO.link());
        }
        if (newsDTO.categories() != null && !newsDTO.categories().isEmpty()) {
            List<Categories> categories = categoriesRepository.findAllById(newsDTO.categories());
            if (categories.isEmpty()) {
                throw new RuntimeException("No valid categories found with the provided IDs");
            }
            existingNews.setCategory(categories);
        }
        
        newsRepository.save(existingNews);
        return convertToNewsDTO(existingNews);
    }

    public String deleteNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id: " + id));
        newsRepository.delete(news);
        return "News deleted successfully";
    }

    private NewsDTO convertToNewsDTO(News news) {
        List<Long> categoryIds = news.getCategory().stream()
                .map(Categories::getId)
                .collect(Collectors.toList());
        
        return new NewsDTO(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getLink(),
                categoryIds
        );
    }
}