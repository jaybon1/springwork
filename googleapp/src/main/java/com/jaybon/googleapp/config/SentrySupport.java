package com.jaybon.googleapp.config;


import org.springframework.context.annotation.Configuration;

// 센트리 관련을 요기서 다 new
import io.sentry.Sentry;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;

@Configuration
public class SentrySupport {
	
	public SentrySupport() {
		System.out.println("=============SentrySupport : init ===============");
		Sentry.init("SDN코드");
	}

    public void logSimpleMessage(String message) {
        // This sends an event to Sentry.
        EventBuilder eventBuilder = new EventBuilder()
                        .withMessage(message) // 메시지를 받아서 보내기
                        .withLevel(Event.Level.ERROR) // 에러레벨을 설정
                        .withLogger(SentrySupport.class.getName());

        // 로그를 센트리로 보낸다
        Sentry.capture(eventBuilder);
    }
}

