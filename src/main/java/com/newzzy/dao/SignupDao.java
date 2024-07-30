package com.newzzy.dao;

import org.springframework.data.repository.CrudRepository;

import com.newzzy.model.Signup;

public interface SignupDao extends CrudRepository<Signup,Integer> {

	Signup save(Signup signup);

	Signup findByUsername(String username);

}
