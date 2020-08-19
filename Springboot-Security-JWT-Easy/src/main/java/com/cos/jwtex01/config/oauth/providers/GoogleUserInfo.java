package com.cos.jwtex01.config.oauth.providers;

import java.util.Map;

public class GoogleUserInfo implements OauthUserInfo{
	
	private Map<String, Object> attribute;

	public GoogleUserInfo(Map<String, Object> attribute) {
		this.attribute = attribute;
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return (String) attribute.get("googleId");
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "google";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String) attribute.get("email");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String) attribute.get("name");
	}
}
