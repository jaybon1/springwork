package com.jaybon.googleapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jaybon.googleapp.config.SentrySupport;

import io.sentry.Sentry;
import io.sentry.SentryClient;

@Controller
public class IndexController {
	
	@Autowired
	private SentrySupport sentrySupport;

	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
	
	@GetMapping({"/product"})
	public String product() {
		//익셉션 터지는 곳에 아래 코드를 입력하면 센트리에 전송된다.
		sentrySupport.logSimpleMessage("프로덕트 오류");
		return "product";
	}
	
	@GetMapping({"user"})
	public String user() {
		//익셉션 터지는 곳에 아래 코드를 입력하면 센트리에 전송된다.
		sentrySupport.logSimpleMessage("유저 오류");
		return "user";
	}
	
}
