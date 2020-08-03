package com.cos.blog.config.aop;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cos.blog.model.User;

public class SessionIntercepter extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		User principal = (User)session.getAttribute("principal");
		if(principal==null) {
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8;");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인이 필요합니다.');");
			out.print("location.href='/auth/loginForm'");
			out.print("</script>");
			return false; // 함수로 진입하지 않고 종료
		}
		
		
		System.out.println("세션을 체크해야함");
		return true; // 함수로 진입
		
	}
	
}


