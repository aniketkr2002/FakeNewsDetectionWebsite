package com.newzzy.model;

import java.util.ArrayList;
import java.util.Date;

public class NewsResponse {

    public String status;
    public int totalResults;
    public ArrayList<Article> articles;

    public static class Article {

        public static class Source {
            public String id;
            public String name;
        }

        public Source source;
        public String author;
        public String title;
        public String description;
        public String url;
        public String urlToImage;
        public Date publishedAt;
        public String content;
    }
}
