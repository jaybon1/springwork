package com.cos.instagram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

	@GetMapping({"","/","image/feed"})
	public String feed() {
		
		return "image/feed"; // 메인페이지가 되는 곳
	}
}
