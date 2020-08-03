package com.jaybon.securityEx01.config.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jaybon.securityEx01.model.User;

import lombok.Data;


//Authentication 객체에 저장할 수 있는 유일한 타입
@Data
public class PrincipalDetails implements UserDetails{
	
	private User user;
	
	public PrincipalDetails(User user) {
		super();
		this.user = user;
	}
	
	

	@Override // 사용자의 비밀번호를 알고 싶으면 호출
	public String getPassword() {
		return user.getPassword();
	}

	@Override // 사용자의 유저네임를 알고 싶으면 호출
	public String getUsername() {
		return user.getUsername();
	}

	@Override // 사용자가 만료된 지를 알고 싶으면 호출
	public boolean isAccountNonExpired() { // 만료안됐니?
		//접속시간확인하여 true false 리턴
		return true;
	}

	@Override 
	public boolean isAccountNonLocked() { // 락 안걸렸니?
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정활성화 되어있니?
		return true;
	}
	
	// Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
	@Override // 어떤 권한을 가졌니?
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority(user.getRole()));
		return authList;
		
	}

}
