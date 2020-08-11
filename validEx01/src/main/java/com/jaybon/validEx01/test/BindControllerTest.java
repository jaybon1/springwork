package com.jaybon.validEx01.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//테스트용 컨트롤러

@RestController
public class BindControllerTest {
	
	@GetMapping("/test/before")
	public void testBefore() {
		System.out.println("testBefore 실행됨");
	}
}
