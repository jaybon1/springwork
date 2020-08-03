package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// Controller, Repository, Configuration, Service, Component
// RestController, Bean

@Service // IOC
public class UserService {

	@Autowired
	private UserRepository userRepository; // DI

	// 커밋이 필요한 기능(select가 아닌 것)은 @Transactional 붙여주자
	@Transactional
	public void 회원가입(User user) {
		user.setRole("ROLE_USER");
		userRepository.save(user);
	}
	
	// "셀렉트만 하는 트랜잭션이다" 라는 것을 명시 readOnly 
	@Transactional(readOnly = true)
	public User 로그인(User user) {
		 return userRepository.login(user);
	}
}
