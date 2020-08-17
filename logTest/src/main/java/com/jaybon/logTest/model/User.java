package com.jaybon.logTest.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	private int id;
	@Size(max = 10, message = "유저네임의 길이가 10자를 초과할 수 없습니다")
	@NotBlank(message = "유저네임이 공백일 수 없습니다")
	private String username;
	@NotBlank(message = "패스워드가 공백일 수 없습니다")
	private String password;
	@NotBlank(message = "이메일이 공백일 수 없습니다")
	@Email
	private String email;
}
