package com.jaybon.jwtEx01.config.jwt;

public interface JwtProperties {
	
	// 인터페이스는 퍼블릭 스태틱 파이널이 생략되어 있다
	String SECRET = "조익현"; // 서버만 알고 있는 비밀값
	int EXPIRATION_TIME = 864000000; // 10일(1/1000초)
	String TOKEN_PREFIX = "Bearer "; // 무조건 1칸 띄워야함
	String HEADER_STRING = "Authorization";
	
}
