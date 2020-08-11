package com.jaybon.validEx01.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.jaybon.validEx01.RespDto;
import com.jaybon.validEx01.StatusCode;

// 공통관심사 : advice
@Component // 모든 어노테이션은 @Component의 자식이다
@Aspect // AOP로 등록
public class BindingAdvice {
	
	
	// *(..) *은 메서드명 (..) 매개변수 0개이상 (*) 매개변수 1개  / * 모든 값 / ..*Controller 모든 하위 Controller / 
	
	// 스프링의 꽃 AOP
	// JoinPoint는 핵심로직이다
	// 핵심로직을 받는다는 것
	// @Before 핵심로직전(request처리)  @After 핵심로직후(request, response 처리)  @Around Before After 바깥
	// @Around는 joinPoint의 데이터를 처리 할 수 있어서 조금 복잡하다
	// execution within 등 확인
	
	
	// @Before @After request 못들고 옴
	
	@Before("execution(* com.jaybon.validEx01.test.BindControllerTest.*(..))") // 포인트 컷 설정
	public void test1() {
		System.out.println("BindControllerTest에 오신 것을 환영합니다.");
	}
	
	@After("execution(* com.jaybon.validEx01.test.BindControllerTest.*(..))") // 포인트 컷 설정
	public void test2() {
		System.out.println();
		System.out.println("BindControllerTest를 이용해주셔서 감사합니다.");
	}
	
	
	// Around는 request response 들고올 수 있음
	// JoinPoint 메서드의 컨텍스트를 들고 올 수 있다
	// 
	@Around("execution(* com.jaybon.validEx01..*Controller.*(..))")
	public Object validationHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		System.out.println("type : "+type);
		System.out.println("method : "+method);
		
		// bindingResult 걸린 메서드 찾기
		Object[] args = proceedingJoinPoint.getArgs(); // 조인포인터의 파라메터
		
		for(Object arg : args) {
			
			// bindingResult가 매개변수로 있다면 bindingResult 저장
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				
				// 에러가 있는지 확인하여 처리
				if(bindingResult.hasErrors()) {
					
					Map<String, String> errorMap = new HashMap<>();
					
					// 에러들을 저장
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					
					RespDto<?> respDto = RespDto.builder()
							.statusCode(StatusCode.FAIL)
							.message("요청에 실패하였습니다.") // 이것도 인터페이스로 만드는게 좋음
							.Data(errorMap)
							.build();
					
					// 저장된 에러들을 리턴
					// 리턴하면 JoinPoint로 가지 않는다
					return new ResponseEntity<RespDto>(respDto, HttpStatus.BAD_REQUEST);
					
				} // if(bindingResult.hasErrors()) 끝
				
			} // if(arg instanceof BindingResult) 끝
			
		} //for(Object arg : args) 끝
		
		// 원래흐름타도록 보내기
		return proceedingJoinPoint.proceed();
	}
	
}