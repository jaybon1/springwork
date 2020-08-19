package com.cos.jwtex01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
	   
	// 크로스오리진 열어주기 설정
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("*"); // e.g. http://domain1.com
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");

      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
   }
   
   // 서버에서 클라이언트로 응답할 때 헤더에 Access-Control-Allow-Headers: * 해줘야함 - 검색
   // 안걸어도 경고만 뜸

}
