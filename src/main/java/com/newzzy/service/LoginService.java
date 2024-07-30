package com.newzzy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newzzy.dao.SignupDao;
import com.newzzy.model.Signup;

@Service 
public class LoginService {
		@Autowired
		private SignupDao signupDao;
		
		public boolean verifyUser(String username, String password) {
	        Signup signup = signupDao.findByUsername(username);
	        if (signup != null && signup.getPassword().equals(password)) {
	            return true;
	        }
	        return false;
	    }
		
}
