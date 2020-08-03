package com.jaybon.securityEx01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jaybon.securityEx01.config.auth.PrincipalDetails;
import com.jaybon.securityEx01.model.User;
import com.jaybon.securityEx01.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping({ "", "/" })
	public @ResponseBody String index() {
		return "인덱스 페이지입니다";
	}

	@GetMapping("/user")
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("확인"+principalDetails);
		System.out.println(principalDetails.getUser().getRole());
		System.out.println(principalDetails.getAuthorities()); // 출력 했을 때 사용자의 모든 권한을 리턴
		return "유저 페이지입니다";
	}

	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "어드민 페이지입니다";
	}

	@GetMapping("/login")
	public String login() {
		return "login"; // 머스태치를 pom에 추가했으니 서픽스는 templates 프리픽스는 .mustatche
	}

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/joinProc")
	public String joinProc(User user) {
		System.out.println("회원가입 진행" + user);
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		userRepository.save(user);
		return "redirect:/";
	}
	
	//@PreAuthorize("hasRole('ROLE_MANAGER')") 구버전
	@Secured("ROLE_MANAGER") // 신버전
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "매니저 페이지입니다";
	}

}
