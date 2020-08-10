package com.jaybon.jwtEx01.config.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaybon.jwtEx01.config.auth.PrincipalDetails;
import com.jaybon.jwtEx01.dto.LoginRequestDto;

import lombok.RequiredArgsConstructor;

// 기본적인 UsernamePasswordAuthenticationFilter가 동작하는게 아니라 내가 만든게 동작하게 된다
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	
	// attemptAuthentication 인증 요청시에 실행되는 함수 / doFilter 필터에 걸면 요청시마다 무조건실행됨
	// /login일때 실행됨
	@Override // Authentication 생성 - AuthenticationManager가 만들어줌
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		System.out.println("UsernamePasswordAuthenticationFilter");
		
		// request에 있는 username과 password를 파싱해서 자바 Object 로받기
		ObjectMapper om = new ObjectMapper();
		LoginRequestDto loginRequestDto = null;
		
		// 파싱하다보면 익셉션이 발생할 수 있기 때문에 try로 묶음
		try {
			
			// getInputStream의 데이터를 클래스에 담아준다(키밸류든 스트링이든)
			loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 내가 커스터 마이징 했기 때문에 UsernamePassword토큰으로 들어오지 않는다 - 직접생성해야됨
		// principal 인증주체 접근주체
		// loginRequestDto
		// authorities ROLE
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
				new UsernamePasswordAuthenticationToken(
						loginRequestDto.getUsername(), 
						loginRequestDto.getPassword());
		
		// Authentication 객체는 authenticationProvider - UserDetailsService를 통해서 만들어진다
		
		// authenticate()함수가 호출되면 authenticationProvider가
		// UserDetailsService의 loadUserByUsername(토큰의 첫번째 파라미터(리퀘스트 받은 값))를 호출하고
		// UserDetails를 리턴 받아서 토큰의 두번째 파라미터 (credential(리퀘스트 받은 값))과
		// UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
		// Authentication 객체를 만들어서 필터체인으로 리턴해준다
		// 팁 : 인증 프로바이더의 디폴트 서비스는 UserDetailsService
		// 팁 : 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
		// 결론은 인증 프로바이더에게 DaoAuthenticationProvider를 알려줄 필요가 없음
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		
		System.out.println("정보확인"+((PrincipalDetails)authentication.getPrincipal()).getUsername());
		
		// 리턴한다는 것은 필터체인을 다시 타는 것
		return authentication;
	}

	@Override // Authentication 만들어지고 나서 실행 / JWT토큰 만듬(response에 정보담기)
	protected void successfulAuthentication(HttpServletRequest request,HttpServletResponse response, 
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		//
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		// JWT토큰을 만든다 . 클레임 삽입
		String jwtToken = JWT.create()
				.withSubject(principalDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 만료시간
				.withClaim("id", principalDetails.getUser().getId()) // 비공개 클레임
				.withClaim("username", principalDetails.getUsername()) // 비공개 클레임
				.sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes())); // getBytes로 바꿔주면 좀 더 안전
				
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
		System.out.println("토큰을 헤더에 넣음");
//		super.successfulAuthentication(request, response, chain, authResult);
	}
}
