package com.jaybon.jwtEx01.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
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
	
	// @AuthenticationPrincipal은 principalDetailsService의 loadUserByUsername를 호출해야만 PrincipalDetails를 사용할 수있다.
	// (Authentication authentication, HttpSession session)
	@GetMapping("user")
	public String user(Authentication authentication) {
		PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("principal : " + principal.getUser().getId());
		System.out.println("principal : " + principal.getUser().getUsername());
		System.out.println("principal : " + principal.getUser().getPassword());
		
		return "<h1>user</h1>";
	}
	
	
	//일반세션용
	@GetMapping("user1")
	public String user(HttpSession session) {
		SessionUser principal = (SessionUser) session.getAttribute("sessionUser");
		System.out.println("principal : " + principal.getId());
		System.out.println("principal : " + principal.getUsername());
		
		return "<h1>user</h1>";
	}
	
	// 매니저 또는 어드민이 접근 가능
	@GetMapping("manager/reports")
	public String reports() {
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
