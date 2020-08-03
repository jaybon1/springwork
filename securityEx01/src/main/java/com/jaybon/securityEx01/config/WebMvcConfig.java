package com.jaybon.securityEx01.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override // 뷰리졸버 설정
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		// 머스태치 리졸버 설정
		MustacheViewResolver resolver = new MustacheViewResolver();
		
		resolver.setCharset("utf-8");
		resolver.setContentType("text/html;charset=utf-8");
		resolver.setPrefix("classpath:/templates"); // 뷰의 위치
		resolver.setSuffix(".html");
	}

}
