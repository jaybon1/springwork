package com.jaybon.springStudy.repository;

import java.util.List;

import com.jaybon.springStudy.model.User;

public interface UserRepository {
	
	public User findById();
	
	public List<User> findAll();
}
