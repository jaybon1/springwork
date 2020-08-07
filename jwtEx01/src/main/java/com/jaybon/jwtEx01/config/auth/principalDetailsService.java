package com.jaybon.jwtEx01.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaybon.jwtEx01.model.User;
import com.jaybon.jwtEx01.repository.UserRepository;

@Service
public class principalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override // 못찾으면 UsernameNotFoundException으로 던져짐
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("UserDetailsService 진입");
		
		User user = userRepository.findByUsername(username);
		if(user != null) {
			System.out.println("해당 유저를 찾았어요");
		}
		return new PrincipalDetails(user);
	}

}

