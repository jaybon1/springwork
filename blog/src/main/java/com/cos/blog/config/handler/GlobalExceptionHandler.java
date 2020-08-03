package com.cos.blog.config.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.handler.exception.MyRoleException;
import com.cos.blog.config.handler.exception.MySessionException;

@ControllerAdvice // IOC로 등록됨. Exception을 낚아채는 컨트롤러. 
@RestController // 데이터 응답. //그냥 컨트롤러를 적으면 파일리턴
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MySessionException.class) // (value = IOException.class) IOException일때만 받는다
	public String sessionException(Exception e) {
		
		// 인증없음
		
		return "<h1>인증 없어요 나가세요</h1>";
	}
	
	@ExceptionHandler(value = MyRoleException.class) // (value = IOException.class) IOException일때만 받는다
	public String roleException(Exception e) {
		
		// 권한없음
		
		return "<h1>권한 없어요 나가세요</h1>";
	}
	
}
