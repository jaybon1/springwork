package com.jaybon.securityEx01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.jaybon.securityEx01.config.oauth.PrincipalOauth2UserService;

@Configuration // IoC 빈(bean, 인스턴스)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 컨트롤러 접근 전에 낚아챔, 특정 주소 접근시 권한 및 인증 미리체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean // IoC에 등록되어 컨피그가 호출될 때 생성, 메서드를 IoC하는 방법
	public BCryptPasswordEncoder enc() { // 마땅히 둘 곳이 없어서 둔 것 Controller를 제외한 곳에 둠
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/admin/1");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); // csrf 비활성화
		
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated()// authenticated() 인증
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')") // access() 권한 .hasAnyRole()
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/login") // 인증이 필요한 곳에 /login 으로 리다이렉트
			.loginProcessingUrl("/loginProc") // 필터체인에서 인지하고 있다가 시큐리티가 낚아채서 Authentication Manager
			.defaultSuccessUrl("/") // 성공하면 해당 주소로 이동 / 슬래시만 달면 이전 주소로 이동
		.and()
			.oauth2Login()
			.loginPage("/loginForm")
			.userInfoEndpoint()
			.userService(principalOauth2UserService);
	}
}

