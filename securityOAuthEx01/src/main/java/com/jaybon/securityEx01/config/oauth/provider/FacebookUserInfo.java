package com.jaybon.securityEx01.config.oauth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo{
	
	private Map<String, Object> attrubutes;

	public FacebookUserInfo(Map<String, Object> attrubutes) {
		this.attrubutes = attrubutes;
	}

	@Override
	public String getProviderId() {
		return (String) attrubutes.get("id");
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
