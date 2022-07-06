package com.hyundai.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import lombok.RequiredArgsConstructor;


/*
 * WebSocketConfigurer를 상속 받은 클래스에서 사용할 handler 클래스와 경로를 설정해주어야한다.
 * .setAllowedOrigins("*")는 ws프로토콜 /ws/chat 하위의 모든 uri에서 chatHandler를 사용한다는 의미이다.
 * .addInterceptors(new HttpSessionHandshakeInterceptor())는 핸드 쉐이크를 할때, http의 session을 가져오기 위한 인터셉터를 추가하는 것이다.*/
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
	private ChatHandler chatHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(chatHandler, "/ws/chat").setAllowedOriginPatterns("*")
			.addInterceptors(new HttpSessionHandshakeInterceptor()); // Interceptor for adding httpsession in to websocket session
	}

}
