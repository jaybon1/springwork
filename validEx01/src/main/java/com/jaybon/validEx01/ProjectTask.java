package com.jaybon.validEx01;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data //모델에는 setter를 넣으면 안된다 - 지금은 연습용 (불변성)
public class ProjectTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 이런 어노테이션은 모델에 거는 것이 아니라 DTO에 걸자(현재는 연습)
	// 예를들어 로그인은 아이디 비밀번호 받는 DTO를 만드는 식
	@Size(max = 10, message = "Summary는 10자를 초과할 수 없습니다.")
	@NotBlank(message = "Summary는 필수 입력 값 입니다.")
	private String summary;
	@NotBlank(message = "acceptanceCriteria는 필수 입력 값 입니다.")
	private String acceptanceCriteria;
	private String status;
	
	//이메일 형식 확인
	@Email
	private String email;
	
}
