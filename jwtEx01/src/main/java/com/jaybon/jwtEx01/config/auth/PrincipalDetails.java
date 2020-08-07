package com.jaybon.jwtEx01.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jaybon.jwtEx01.model.User;

public class PrincipalDetails implements UserDetails{
	
	private User user;
	

	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// setter는 바뀌지 못하도록 안만듬 - 회원정보 수정시 필요함
	
	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		user.getRoleList().forEach(r ->{
			authorities.add(()->{return r;});
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
