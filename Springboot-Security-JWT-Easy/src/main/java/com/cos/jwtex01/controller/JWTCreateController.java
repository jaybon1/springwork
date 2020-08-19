package com.cos.jwtex01.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwtex01.config.jwt.JwtProperties;
import com.cos.jwtex01.config.oauth.providers.GoogleUserInfo;
import com.cos.jwtex01.config.oauth.providers.OauthUserInfo;
import com.cos.jwtex01.model.User;
import com.cos.jwtex01.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// 인증 컨트롤러 - jwt인증만을 위한 컨트롤러

@RestController
@RequiredArgsConstructor
public class JWTCreateController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/oauth/jwt/google")
	public String jwtCreate(@RequestBody Map<String, Object> data) {
		
		// Map으로 받은데이터를 조건에 맟춰서 분기하여 google, facebook 등을 세팅
		
		System.out.println("jwtCreate실행됨");
		System.out.println(data.get("profileObj"));
		
		OauthUserInfo googleUserInfo = new GoogleUserInfo((Map<String, Object>) data.get("profileObj"));
		System.out.println(googleUserInfo.getName());
		
		User userEntity = userRepository.findByUsername(googleUserInfo.getProvider()+"_"+googleUserInfo.getProviderId());
		
		if(userEntity == null) {
			User userRequest = User.builder()
					.username(googleUserInfo.getProvider()+"_"+googleUserInfo.getProviderId())
					.password(bCryptPasswordEncoder.encode("겟인데어"))
					.email(googleUserInfo.getEmail())
					.provider(googleUserInfo.getProvider())
					.providerId(googleUserInfo.getProviderId())
					.roles("ROLE_USER")
					.build();

			// jpa는 save하면 저장한 테이블을 리턴한다
			userEntity = userRepository.save(userRequest);
		}
		
		// 토큰 만들기
		String jwtToken = JWT.create()
				.withSubject(googleUserInfo.getProvider()+"_"+googleUserInfo.getProviderId())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
				.withClaim("id", userEntity.getId())
				.withClaim("username", userEntity.getUsername())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		
		return jwtToken; //리턴을 jwt담아서 보내줄 것
	}

}
