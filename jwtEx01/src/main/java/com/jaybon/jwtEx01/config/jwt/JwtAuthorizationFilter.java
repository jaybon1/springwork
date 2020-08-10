package com.jaybon.jwtEx01.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jaybon.jwtEx01.config.auth.PrincipalDetails;
import com.jaybon.jwtEx01.config.auth.SessionUser;
import com.jaybon.jwtEx01.dto.LoginRequestDto;
import com.jaybon.jwtEx01.model.User;
import com.jaybon.jwtEx01.repository.UserRepository;

// BasicAuthenticationFilter 헤더 전문 필터
// 헤더 검증해서 시큐티리컨텍스트홀더에 넣어주는 필터인데 넣어줄지 말지 고민해보자
// 내가 new 한 클래스는 Autowired가 안된다

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	
	private UserRepository userRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}
	
	
	// JwtAuthenticationFilter.java가 끝나면 이쪽으로 넘어온다
	// 서명해야함
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("doFilterInternal");
			
		// 헤더값 확인
		String authorization = request.getHeader(JwtProperties.HEADER_STRING); // Authorization확인
		
		// header.startsWith(JwtProperties.TOKEN_PREFIX) 문자열 앞에 해당 문자열이 있는지 확인
		if(authorization == null || !authorization.startsWith(JwtProperties.TOKEN_PREFIX)) {
			
			System.out.println("돌려보내기");
			
			chain.doFilter(request, response); // 건드리지 않은 request, response 그냥 다시 돌려보냄
			
		} else { // 토큰이 정상적으로 들어오면 서명을 한다
			
			System.out.println("토큰있음");
			System.out.println(authorization);
			
			// JWT토큰 주의점 - 공백이 있으면 안된다. / = 등의 패딩이 들어가있으면 안된다.
			// 토큰 프리픽스와 공백과 패딩 등을 제거한다
			String token = authorization
					.replace(JwtProperties.TOKEN_PREFIX, "")
					.replace(" ", "")
					.replace("=", "");
			
			System.out.println("token "+token);
			
			// 토큰 검증(로그인없이 이용자 인증 끝)
			// 서명하고 끝내려고 하기 때문에 로그인 할 필요가 없다. 그래서 매니저 사용안함 
			// 내가 SecurityContext에 직접 접근해서 세션을 만들 때 자동으로 UserDetailsService에 있는 loadUserByUsername호출
			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
					.verify(token)
					.getClaim("username").asString(); // asString을 사용하여 클레임의 내용을 가져온다
			
			// 1번방식
			System.out.println(username);
			
			if (username != null) {
//				AuthenticationManager authenticationManager = getAuthenticationManager(); // 매니저 가져오기
				
				System.out.println("userRepository " + userRepository);
				
				User user = userRepository.findByUsername(username);
				
				PrincipalDetails principal = new PrincipalDetails(user);
				
				System.out.println("user "+user);
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(
								user.getUsername(), 
								null, // 로그인할 할 것이 아니기 때문에 비번을 넣을 필요가 없다
								principal.getAuthorities());
				
				Authentication authentication = usernamePasswordAuthenticationToken;
				
				// 서명하고 끝내려고 하기 때문에 로그인 할 필요가 없다. 그래서 매니저 사용안함 
//				Authentication authentication 
//					= authenticationManager.authenticate(usernamePasswordAuthenticationToken);
				
				
//				// 11
				System.out.println("authentication");
				
				SessionUser sessionUser = SessionUser.builder()
						.id(user.getId())
						.username(user.getUsername())
						.roles(user.getRoleList())
						.build();
						
				HttpSession session = request.getSession();
				session.setAttribute("sessionUser", sessionUser);
				// 11
				
				// 세션저장공간
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				System.out.println("SecurityContextHolder");
				
			}
			// 1번방식 끝
			
			
 			// 2번방식 끝
//			if(username != null) {
//				User user = userRepository.findByUsername(username);
//				SessionUser sessionUser = SessionUser.builder()
//						.id(user.getId())
//						.username(user.getUsername())
//						.roles(user.getRoleList())
//						.build();
//						
//				HttpSession session = request.getSession();
//				session.setAttribute("sessionUser", sessionUser);
//			}
			// 2번방식 끝
			
			// 3번방식
			// 클레임에 ROLE을 넣어서
			// 3벙방식 끝
			
			chain.doFilter(request, response);
		}
	}
}













