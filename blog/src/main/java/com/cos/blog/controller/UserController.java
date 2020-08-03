package com.cos.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.controller.dto.CommonRespDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 컨트롤러는 무조건 서비스를 호출해야한다
	
	// 응답을 data로 받기위해서 responsebody 추가
	// (User user)를 이용하면 xxx-form-urlencoded 만 받아진다
	// (@RequestBody User user)를 이용하면 
	// jwt 등 공부하기
	
	@PostMapping("/auth/joinProc")
	public @ResponseBody CommonRespDto<?> joinProc(@RequestBody User user) { 
		
		// 제네릭 코드를 모르겠다면 ?를 넣는다
		// 페이지요청이 아니면 무조건 CommonRespDto
		userService.회원가입(user);
		
		return new CommonRespDto<String>(1, "회원가입 결과 성공");
	}
	
	@PostMapping("/auth/loginProc")
	public @ResponseBody CommonRespDto<?> loginProc(@RequestBody User user, HttpSession session){
		// request가 들고있는 것은 모두 매개변수에 넣어서 가져올 수 있다 (request포함)
		
		User persistUser = userService.로그인(user); // DB에서 가져온 정보이기 때문에 퍼시스턴스
		
		// 퍼시스턴스란 rs를 자바 객체에 넣어 주는 것
		
		if(ObjectUtils.isEmpty(persistUser)) { // ObjectUtils.isEmpty 빈객체를 리턴받았을 경우 트루
			return new CommonRespDto<String>(-1, "로그인 결과 실패");
		} else {
			// 세션등록 해야함
			// 톰캣은 리퀘스트 리스폰스만 만들어준다
			// 리퀘스트 안에 세션이 있는 것은 아니고 메모리 접근 주소를 가지고 있다
			
			session.setAttribute("principal", persistUser);
			
			return new CommonRespDto<String>(1, "로그인 결과 성공");
		}
		
	}
	
}
