package com.jaybon.securityEx01.config.oauth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.jaybon.securityEx01.config.auth.PrincipalDetails;
import com.jaybon.securityEx01.config.oauth.provider.FacebookUserInfo;
import com.jaybon.securityEx01.config.oauth.provider.GoogleUserInfo;
import com.jaybon.securityEx01.config.oauth.provider.NaverUserInfo;
import com.jaybon.securityEx01.config.oauth.provider.OAuth2UserInfo;
import com.jaybon.securityEx01.model.User;
import com.jaybon.securityEx01.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	
		
		OAuth2User oAuth2User = super.loadUser(userRequest); // 토큰값으로 자원을 받아오는 함수
		
		// 1번: oAuth2User의 정보를 principalDetails에 넣어주면 된다.
		// 2번: PrincipalDetails를 리턴한다.
		System.out.println("userRequest"+userRequest.getAccessToken().getTokenValue()); // 코드 토큰 유저정보
		System.out.println("userRequest"+userRequest.getClientRegistration()); // 코드 토큰 유저정보
		System.out.println(oAuth2User); // 토큰을 통해 응답받은 회원정보
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		return processOAuth2User(userRequest, oAuth2User); // 이때 세션에 등록된다 OAuth2User
	}
	
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		
		// 일반적으로는 로그인할 때 유저정보 User
		// 1번 : OAuth2로 로그인할 때 유저정보 oAuth2User.getAttributes() <- 구성해야됨
		// 2번 : DB에 이 사람있나?
		// 있으면? -> 업데이트(구글에서 바뀐정보가 있을 수 있으니 확인 없이 무조건 업데이트)
		// 없으면? -> 인서트(회원가입)
		// 리턴을 PrincipalDetails를 리턴
		
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
		} else {
			System.out.println("구글, 페이스북, 네이버만 가능");
		}
		

		Optional<User> userOptional 
			= userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());
		
		User user;
		if(userOptional.isPresent()) {
			user = userOptional.get();
			// 아이디가 있어도 update 해줘야 함
		} else {
			user = User.builder()
					.username(oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId())
					.email(oAuth2UserInfo.getEmail())
					.role("ROLE_USER")
					.provider(oAuth2UserInfo.getProvider())
					.providerId(oAuth2UserInfo.getProviderId())
					.build();
			userRepository.save(user);
		}
			
		return new PrincipalDetails(user, oAuth2User.getAttributes());
	}
}
