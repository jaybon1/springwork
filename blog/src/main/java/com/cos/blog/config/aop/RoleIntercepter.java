package com.cos.blog.config.aop;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cos.blog.config.handler.exception.MyRoleException;
import com.cos.blog.config.handler.exception.MySessionException;
import com.cos.blog.model.User;

public class RoleIntercepter extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			
			System.out.println("RoleIntercepter : 인증이 안됨");
			throw new MySessionException(); // 익셉션을 개발자가 낚아챈다, 세션익셉션
			
		} else {
			
			if(!principal.getRole().equals("ROLE_ADMIN")) {
				System.out.println("RoleIntercepter : 권한이 없음");
				throw new MyRoleException(); // 익셉션을 개발자가 낚아챈다, 롤익셉션
			}
			
		}
		
		return true;
		
	}
	
}
