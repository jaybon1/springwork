package com.spring.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.model.User;
import com.spring.test.service.UserService;


@RestController
public class RController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/userdata")
	public List<User> userData(Model model) {
		
		List<User> users = userService.유저정보보기();
		
		return users;
	}
}
