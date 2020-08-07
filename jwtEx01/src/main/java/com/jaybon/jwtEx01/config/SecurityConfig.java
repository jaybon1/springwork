package com.jaybon.jwtEx01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jaybon.jwtEx01.config.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// csrf() - form태그 요청 관련 공격을 막는 것
		http
			.csrf().disable() // 폼태그를 사용하지 않을 경우
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.formLogin().disable() // 폼태그 로그인 막겠다
			.httpBasic().disable() // jsessionId 안쓰겠다
			// 기본적인 UsernamePasswordAuthenticationFilter가 동작하는게 아니라 내가 만든게 동작하게 된다
			.addFilter(new JwtAuthenticationFilter(authenticationManager())) // 나의 인증필터 /login 낚아챔
//			.addFilter(null) // 나의 권한필터
			.authorizeRequests()
			.antMatchers("/api/v1/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			.antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll();
	}
}
