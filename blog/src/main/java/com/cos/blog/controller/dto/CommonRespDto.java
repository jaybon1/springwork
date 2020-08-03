package com.cos.blog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonRespDto<T> {
	private int statusCode; // 응답코드, enum으로 만드는 게 좋지만 여기선 그냥한다. / 1 정상 / -1 실패 / 0 변경안됨
	private T data; // 제네릭으로 만들면 데이터를 공통으로 보내줄 수 있다 // select를 할경우
}
