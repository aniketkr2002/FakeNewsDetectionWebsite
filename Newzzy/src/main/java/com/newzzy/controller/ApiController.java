package com.newzzy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newzzy.model.Article;
import com.newzzy.service.ArticleService;
import com.newzzy.service.NewsResponseService;

public class ApiController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private NewsResponseService newsResponseService; 
	
	@GetMapping
    public ResponseEntity<List<Article>> fetchNews() {
        List<Article> articles = newsResponseService.getAllNews();
        return ResponseEntity.ok(articles);
    }
	
	@PostMapping("/articles/{articleId}/rate")
    public ResponseEntity<Object> rateArticle(
            @PathVariable Long articleId,
            @RequestParam int rating
    ) {
        Article ratedArticle = articleService.rateArticle(articleId, rating);
        
        return ResponseEntity.ok(ratedArticle);
    }
	
	
	
	@GetMapping("/falseNews")
    public ResponseEntity<?> getFalseNews() {
        // Assuming you have a method to get false news
        List<Article> falseNewsList = newsResponseService.getAllFalseNews();
        return ResponseEntity.ok(falseNewsList);
    }

}
