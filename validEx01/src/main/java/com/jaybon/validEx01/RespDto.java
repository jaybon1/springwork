package com.jaybon.validEx01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespDto<T> {
	
	// statusCode 성공 -> data 읽기
	// statusCode 실패 -> message 읽기
	private int statusCode; // 코드를 인터페이스로 정리하거나 HTTP상태코드 이용
	private String message; // 상태 메시지
	
	// 데이터 담기
	private T Data;

}
