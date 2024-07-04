package com.newzzy.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newzzy.model.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long>{

}
