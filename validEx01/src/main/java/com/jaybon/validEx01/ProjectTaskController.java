package com.jaybon.validEx01;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/board")
public class ProjectTaskController {
	
	@Autowired // @Autowired로 DI를 주입하려면 @RestController를 IOC를 해야함
	private ProjectTaskRepository projectTaskRepository;
	
	
	// @Valid 밸리데이션체크해서 익셉션터지면 bindingResult 로 보냄
	// @Valid를 붙이면 필터에서 모든 걸 검증해서 bindingResult에 넣는 것
	
	@PostMapping({"","/"})
	public ResponseEntity<?> save(@Valid @RequestBody ProjectTask repuestProjectTask, BindingResult bindingResult){
		
		ProjectTask entityProjectTask = projectTaskRepository.save(repuestProjectTask);
		
		RespDto<?> respDto = RespDto.builder()
				.statusCode(StatusCode.OK)
				.message("요청에 성공하였습니다.") // 이것도 인터페이스로 만드는게 좋음
				.Data(entityProjectTask)
				.build();

		// HttpStatus.CREATED = 201
		// return new ResponseEntity<Integer>(1, );
		return new ResponseEntity<RespDto>(respDto, HttpStatus.CREATED);
	}
	
}
