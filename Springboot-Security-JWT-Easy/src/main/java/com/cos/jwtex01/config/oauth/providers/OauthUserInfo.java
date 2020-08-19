package com.cos.jwtex01.config.oauth.providers;

public interface OauthUserInfo {
	
	String getProviderId();

	String getProvider();

	String getEmail();

	String getName();
	
}
