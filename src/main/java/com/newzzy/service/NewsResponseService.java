package com.newzzy.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.newzzy.dao.NewsRepository;
import com.newzzy.model.News;
import com.newzzy.model.NewsResponse;
import com.newzzy.model.NewsResponse.Article;

@Service
public class NewsResponseService {

    private static final String   apiKey = "a216a5dfa4d84236b42c12e354f7f6c0"; // Use your actual API key here
    private static final String API_URL = "https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=API_KEY";
   
    
    @Autowired
    private RestTemplate restTemplate;
    
    
    @Autowired
    private NewsRepository newsRepository;

    private static final String API_URL1 = "https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY";

    public Iterable<News> fetchAndSaveNews() {
        String finalApi = API_URL1.replace("API_KEY", apiKey);
        ResponseEntity<NewsResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, NewsResponse.class);
        NewsResponse body = response.getBody();

        if (body != null && body.articles != null) {
            List<News> newsList = body.articles.stream().map(article -> {
                News news = new News();
                news.setHeadline(article.title);
                news.setDescription(article.description);
                news.setPublishedAt(article.publishedAt);
                news.setLocation(article.source.name);
                return news;
            }).collect(Collectors.toList());
            List<News> newNewsList = newsList.stream()
                    .filter(news -> {
                        boolean exists = newsRepository.existsByHeadlineAndPublishedAt(news.getHeadline(), news.getPublishedAt());
                        System.out.println("News exists check for headline '" + news.getHeadline() + "' and publishedAt '" + news.getPublishedAt() + "': " + exists);
                        return !exists;
                    })
                    .collect(Collectors.toList());
        
            if (newNewsList.isEmpty()) {
                return newsRepository.findAll();
            }

            return newsRepository.saveAll(newNewsList);
        }

        return null;
    }

    public Iterable<News> getNewsDetails() {
        return newsRepository.findAll();
    }
    
    
    
    
    
    
    
    
    
    
    
    public List<NewsResponse.Article> getNews(){
        String finalApi = API_URL.replace("API_KEY", apiKey);
        ResponseEntity<NewsResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, NewsResponse.class);
        NewsResponse body = response.getBody();
        return body != null ? body.articles : null;
    }
    
    private static final String API_URL_INDIAN ="https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY";

    public List<NewsResponse.Article> getNews1(){
        String finalApi = API_URL_INDIAN.replace("API_KEY", apiKey);
        ResponseEntity<NewsResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, NewsResponse.class);
        NewsResponse body = response.getBody();
        return body != null ? body.articles : null;
    }
    private static final String HEALTH_API="https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=API_KEY";
    public List<NewsResponse.Article> getHealthNews(){
        String finalApi = HEALTH_API.replace("API_KEY", apiKey);
        ResponseEntity<NewsResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, NewsResponse.class);
        NewsResponse body = response.getBody();
        return body != null ? body.articles : null;
    }
    private static final String TOPHEADLINE_API= "https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY";
    public List<NewsResponse.Article> getTopNews(){
        String finalApi = TOPHEADLINE_API.replace("API_KEY", apiKey);
        ResponseEntity<NewsResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, NewsResponse.class);
        NewsResponse body = response.getBody();
        return body != null ? body.articles : null;
    }
    private static final String SPORTS_API="https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=API_KEY";
    public List<NewsResponse.Article> getSportsNews(){
        String finalApi = SPORTS_API.replace("API_KEY", apiKey);
        ResponseEntity<NewsResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, NewsResponse.class);
        NewsResponse body = response.getBody();
        return body != null ? body.articles : null;
    }
    private static final String ENTERTAINMENT_API="https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=API_KEY";
    public List<NewsResponse.Article> getEntertainmentNews(){
        String finalApi = ENTERTAINMENT_API.replace("API_KEY", apiKey);
        ResponseEntity<NewsResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, NewsResponse.class);
        NewsResponse body = response.getBody();
        return body != null ? body.articles : null;
    }
	

	


   
}
