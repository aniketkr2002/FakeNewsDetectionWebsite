package com.newzzy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newzzy.dao.RateRepositoryDao;
import com.newzzy.model.Rating;

@Service
public class RateService {
		@Autowired
		RateRepositoryDao rateRepositoryDao;
		
		public void rateNews(Long new_id,Long rate,String userName) {
			
			Rating r = new Rating();
			r.setNew_id(new_id);
			r.setRate(rate);
			r.setRatedByuserName(userName);
			rateRepositoryDao.save(r);
			
			
		}
		
}
