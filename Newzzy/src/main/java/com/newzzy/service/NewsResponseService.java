package com.newzzy.service;

import com.newzzy.model.NewsResponse;
import com.newzzy.model.Rate;
import com.newzzy.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsResponseService {

    private static final String API_KEY = "a216a5dfa4d84236b42c12e354f7f6c0"; // Use your actual API key here
    private static final String API_URL = "https://newsapi.org/v2/top-headlines?country=de&category=business&apiKey=" + API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    private final List<Article> falseNewsList = new ArrayList<>();

    public List<Article> getAllNews() {
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Send GET request and parse response
        ResponseEntity<NewsResponse> responseEntity = restTemplate.exchange(API_URL, HttpMethod.GET, entity, NewsResponse.class);

        // Check if the response is successful
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            NewsResponse newsResponse = responseEntity.getBody();
            if (newsResponse != null && "ok".equals(newsResponse.getStatus())) {
                // Filter articles to exclude those with an average rating below 2
                return newsResponse.getArticles().stream()
                        .filter(article -> {
                            double averageRating = article.getRates().stream()
                                    .mapToInt(Rate::getRating)
                                    .average()
                                    .orElse(5.0); // Default to 5.0 if no ratings available
                            return averageRating >= 2;
                        })
                        .collect(Collectors.toList());
            }
        }

        // In case of error or no articles, return an empty list
        return List.of();
    }

    public List<Article> getAllFalseNews() {
        return new ArrayList<>(falseNewsList);
    }

    public void addFalseNews(Article article) {
        falseNewsList.add(article);
    }
}
