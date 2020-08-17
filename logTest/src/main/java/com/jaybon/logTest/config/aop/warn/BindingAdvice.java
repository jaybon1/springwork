package com.jaybon.logTest.config.aop.warn;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

//밸리데이션 체크

@Configuration
@Aspect
public class BindingAdvice {

	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

	// JoinPoint를 주입
	@Around("execution(* com.jaybon.logTest.controller..*Controller.*(..))")
	public Object bindingPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		Object[] args = proceedingJoinPoint.getArgs();

		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName() + " : ";
		String methodName = proceedingJoinPoint.getSignature().getName() + "() ";

		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if (bindingResult.hasErrors()) {
//					System.out.println("뭔가 잘못 입력됨");
					Map<String, String> errorMap = new HashMap<>();
					for (FieldError error : bindingResult.getFieldErrors()) {

						log.info("인포");
						log.debug("디버그");
						log.error("에러");
						log.warn(type + methodName +error.getDefaultMessage());

						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					return new ResponseEntity<Map>(errorMap, HttpStatus.BAD_REQUEST);
				}

			}
		}

		return proceedingJoinPoint.proceed();
	}
}
