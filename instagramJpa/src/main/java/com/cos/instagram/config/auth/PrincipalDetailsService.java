package com.cos.instagram.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(PrincipalDetailsService.class);
	private final UserRepository userRepository;
	
	// 일반 로그인용 서비스
	// 해당 함수가 정상적으로 리턴되면 @AuthenticationPrincipal 어노테이션 활성화됨 - 여길 타지 않으면 어노테이션 활성화 안됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername : username : "+username);
		User userEntity = 
				userRepository.findByUsername(username).get(); // 널처리 해야됨
		return new PrincipalDetails(userEntity);
	}

}
