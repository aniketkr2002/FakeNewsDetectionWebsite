package com.newzzy.service;

import com.newzzy.dao.SignupDao;
import com.newzzy.exception.UsernameAlreadyExistsException;
import com.newzzy.model.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private SignupDao signupDao;

    public void registerUser(Signup signup) throws UsernameAlreadyExistsException {
        if (signupDao.findByUsername(signup.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }else
           signupDao.save(signup);
    }
}
