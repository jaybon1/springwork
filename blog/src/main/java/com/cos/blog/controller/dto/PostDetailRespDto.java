package com.cos.blog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 웬만하면 콤포지션 하지말기!!
// 필드명을 그냥 적기!!

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailRespDto {
	
	private int id;
	private String title;
	private String content;
	private String username;
	
}
