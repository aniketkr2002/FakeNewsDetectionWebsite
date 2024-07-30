package com.newzzy.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newzzy.model.News;
import com.newzzy.model.NewsResponse;
import com.newzzy.service.NewsResponseService;
import com.newzzy.service.RateService;
@RestController
@RequestMapping(value="/news")
public class NewsApiController {
	
	@Autowired
	private NewsResponseService newsResponseService; 
	
	@GetMapping("/fetch")
	public ResponseEntity<?> fetchAndSaveNews() {
	    try {
	        Iterable<News> newsList = newsResponseService.fetchAndSaveNews();
	        if (newsList != null) {
	            return new ResponseEntity<>(newsList, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return new ResponseEntity<>("Failed to fetch and save news", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

    @GetMapping(value="/science")
    public ResponseEntity<List<NewsResponse.Article>> fetchNews() {
        List<NewsResponse.Article> articles = newsResponseService.getNews();
        return ResponseEntity.ok(articles);
    }
    @GetMapping(value="/indian")
    public ResponseEntity<List<NewsResponse.Article>> fetchNews1() {
        List<NewsResponse.Article> articles = newsResponseService.getNews1();
        return ResponseEntity.ok(articles);
    }
    @GetMapping(value="/health")
    public ResponseEntity<List<NewsResponse.Article>> fetchHealthNews() {
        List<NewsResponse.Article> articles = newsResponseService.getHealthNews();
        return ResponseEntity.ok(articles);
    }
    @GetMapping(value="/top")
    public ResponseEntity<List<NewsResponse.Article>> fetchSportsNews() {
        List<NewsResponse.Article> articles = newsResponseService.getSportsNews();
        return ResponseEntity.ok(articles);
    }
    @GetMapping(value="/sports")
    public ResponseEntity<List<NewsResponse.Article>> fetchTopNews() {
        List<NewsResponse.Article> articles = newsResponseService.getTopNews();
        return ResponseEntity.ok(articles);
    }
   
    @GetMapping(value="/entertainment")
    public ResponseEntity<List<NewsResponse.Article>> fetchEntertainmentNews() {
        List<NewsResponse.Article> articles = newsResponseService. getEntertainmentNews();
        return ResponseEntity.ok(articles);
    }
    
    @Autowired
    private RateService ratingService;
	
	@PutMapping("/rate")
    public ResponseEntity<String> rateNews(@RequestParam Long news_id,@RequestParam Long rate, @RequestParam String userName) {
        try {
            ratingService.rateNews(news_id,rate, userName);
            return ResponseEntity.ok("Rating saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
	

}
