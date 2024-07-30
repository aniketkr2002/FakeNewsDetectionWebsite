package com.newzzy.dao;

import org.springframework.data.repository.CrudRepository;
import com.newzzy.model.Rating;

public interface RateRepositoryDao extends CrudRepository<Rating, Long>{

}
