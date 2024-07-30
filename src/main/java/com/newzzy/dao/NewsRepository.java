package com.newzzy.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newzzy.model.News;

@Repository
public interface NewsRepository extends CrudRepository<News , Long> {

	boolean existsByHeadlineAndPublishedAt(String headline, Date publishedAt);
}