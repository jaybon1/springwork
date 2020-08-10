package com.jaybon.jwtEx01.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaybon.jwtEx01.config.auth.PrincipalDetails;
import com.jaybon.jwtEx01.config.auth.SessionUser;
import com.jaybon.jwtEx01.model.User;
import com.jaybon.jwtEx01.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1") // 공통 진입경로
//@CrossOrigin // CORS 허용
public class RestApiController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 모든 사람이 접근 가능
	@GetMapping("home")
	public String home() {
		return "<h1>home</h1>";
	}
	
	@GetMapping("user")
	public String user(HttpSession session) {
		SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
		System.out.println("principal : "+sessionUser.getId());
		System.out.println("principal : "+sessionUser.getUsername());
		System.out.println("principal : "+sessionUser.getRoles());
		return "<h1>user</h1>";
	}
	
	// 매니저 또는 어드민이 접근 가능
	@GetMapping("manager/reports")
	public String reports(@AuthenticationPrincipal PrincipalDetails principalDetails) {

		return "<h1>reports</h1>";
	}
	
	// 어드민이 접근 가능
	@GetMapping("admin/user")
	public List<User> Users() {
		return null;
	}
	
	@PostMapping("join")
	public String join(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles("ROLE_USER");
		userRepository.save(user);
		return "회원가입완료";
	}
}
