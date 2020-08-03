package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.blog.config.aop.RoleIntercepter;
import com.cos.blog.config.aop.SessionIntercepter;

// 필터링
@Configuration
public class WebConfig implements WebMvcConfigurer { // web.xml 처럼작동한다
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new SessionIntercepter())
			.addPathPatterns("/user/**")
			.addPathPatterns("/post/**")
			.addPathPatterns("/post**");
		
		registry.addInterceptor(new RoleIntercepter())
			.addPathPatterns("/admin/**");
		
	}
}
