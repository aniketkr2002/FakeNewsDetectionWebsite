package com.newzzy.service;

import com.newzzy.dao.ArticleRepository;
import com.newzzy.model.Article;
import com.newzzy.model.Rate;
import com.newzzy.customException.EntityNotFoundException;
import com.newzzy.customException.FalseNewsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

   

    @Autowired
    private NewsResponseService newsResponseService;

    @Transactional
    public Article rateArticle(Long articleId, int rating) {
        // Find the article by ID or throw EntityNotFoundException
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article with id " + articleId + " not found"));

        // Initialize article with default rating of 5 if not already rated
        if (article.getRates().isEmpty()) {
            Rate defaultRate = new Rate();
            defaultRate.setArticle(article);
            defaultRate.setRating(5);
            article.getRates().add(defaultRate);
        }

        // Add new rating
        Rate rate = new Rate();
        rate.setArticle(article);
        rate.setRating(rating);
        article.getRates().add(rate);

        // Calculate average rating and handle false news
        calculateAndHandleFalseNews(article);

        return articleRepository.save(article);
    }

    private void calculateAndHandleFalseNews(Article article) {
        List<Rate> rates = article.getRates();
        int sum = rates.stream().mapToInt(Rate::getRating).sum();
        double averageRating = (double) sum / rates.size();

        // If average rating falls below 2, move article to false news list
        if (averageRating < 2.0) {
            moveArticleToFalseNews(article);
        }
    }

    private void moveArticleToFalseNews(Article article) {
        // Perform logic to move article to false news list
        newsResponseService.addFalseNews(article);
        // Here you can implement further logic to handle false news, like sending to another API
        throw new FalseNewsException("Article identified as false news");
    }

}
