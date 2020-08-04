package com.jaybon.securityEx01.config.oauth.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{
	
	private Map<String, Object> attrubutes;

	public GoogleUserInfo(Map<String, Object> attrubutes) {
		this.attrubutes = attrubutes;
	}

	@Override
	public String getProviderId() {
		return (String) attrubutes.get("sub");
	}

	@Override
	public String getProvider() {
		return "google";
	}

	@Override
	public String getEmail() {
		return (String) attrubutes.get("email");
	}

	@Override
	public String getName() {
		return (String) attrubutes.get("name");
	}
}
