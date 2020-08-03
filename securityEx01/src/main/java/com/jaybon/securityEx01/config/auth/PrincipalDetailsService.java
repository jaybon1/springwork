package com.jaybon.securityEx01.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaybon.securityEx01.model.User;
import com.jaybon.securityEx01.repository.UserRepository;

// UserDetailsService는  IoC로 찾음

@Service // UserDetailsService타입으로 메모리에 뜬다 (덮어씌워짐)
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 어썬티케이션 매니저가 낚아챔
		// JPA는 기본적인 CRUD만 있어서 다른걸 쓰려면 만들어줘야함 
		
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			return null;
		}
		
		return new PrincipalDetails(user);
	}

}
