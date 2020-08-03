package com.jaybon.securityEx01.config.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.jaybon.securityEx01.model.User;

import lombok.Data;


//Authentication 객체에 저장할 수 있는 유일한 타입
@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
	
	private User user;
	
	private Map<String, Object> attributes;
	
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


	// OAuth2User 오버라이딩
	
	// 리소스 서버로 부터 받는 회원정보
	// OAuth2User타입의 Map으로 저장되서 나중에 꺼내쓸 수있도록 한다
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	// 현재로서는 이해하기 어려움
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "제공자 ID";
	}

}
