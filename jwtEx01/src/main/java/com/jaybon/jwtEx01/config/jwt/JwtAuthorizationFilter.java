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
			
			//검증끝!!!!!!!!!!!!!!!!!!!!!
			
			
			// 1번방식
//			System.out.println(username);
//			
//			if (username != null) {
////				AuthenticationManager authenticationManager = getAuthenticationManager(); // 매니저 가져오기
//				
//				// 유저네임으로 리파지토리에서 유저 정보가져오기
//				User user = userRepository.findByUsername(username);
//				
//				// UserDetails 타입으로 변환
//				PrincipalDetails principal = new PrincipalDetails(user);
//				
//				// usernamePasswordAuthenticationToken 토큰만들기
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//						new UsernamePasswordAuthenticationToken(
//								user.getUsername(), 
//								null, // 로그인할 할 것이 아니기 때문에 비번을 넣을 필요가 없다
//								principal.getAuthorities());
//				
//				// 매니저를 사용하지 않고 바로 Authentication화 시켜버린다.
//				Authentication authentication = usernamePasswordAuthenticationToken;
//				
//				// 서명하고 끝내려고 하기 때문에 로그인 할 필요가 없다. 그래서 매니저 사용안함 
////				Authentication authentication 
////					= authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//				
//				// 세션저장공간에 Authentication을 넣는다
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//				
//			}
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
			// 클레임에 ROLE을 넣어서 바로어썬티케이션 만들기
			// 3번방식 끝
			
			// 4번방식
			if(username != null) {	
				User user = userRepository.findByUsername(username);
				
				// 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해 
				// 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
				PrincipalDetails principalDetails = new PrincipalDetails(user);
				Authentication authentication =
						new UsernamePasswordAuthenticationToken(
								principalDetails, //나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.(인증하는거 아니니까 유저네임이 아닌 유저객체를 넣어도 무방)
								null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
								principalDetails.getAuthorities());
				
				// 강제로 시큐리티의 세션에 접근하여 값 저장
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
//				HttpSession session = request.getSession();
//				session.setAttribute("sessionUser", user); // 템플릿을 찾을 경우 사용해도된다.(sessionScope.user)
				
			}
			// 4번방식
			
			chain.doFilter(request, response);
		}
	}
}













