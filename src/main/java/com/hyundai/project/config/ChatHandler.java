package com.hyundai.project.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

/*
 * @author ksj
 * @decription chatHandler
 * */
@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler{
	
	private static List<String> onlineList = new ArrayList<>();
	private static List<WebSocketSession> sessionList = new ArrayList<>();
	Map<String, WebSocketSession> userSession = new HashMap<>();
	
	ObjectMapper json = new ObjectMapper();
	
	//message
	//webSocket을 통해서 받은 메세지를 처리하는 메소드 
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		//json test
		Map<String, Object> dataMap = new HashMap<>();
		
		//master status
		String masterStatus = null;
		if(userSession.containsKey("master")) {
			masterStatus = "online";
		}else {
			masterStatus = "offline";
		}
		
		//sendting time
		LocalDateTime currentTime = LocalDateTime.now();
		String time = currentTime.format(DateTimeFormatter.ofPattern("hh:mm a, E"));
		
		//message data
		String senderId = (String) session.getAttributes().get("sessionId");	//메세지를 보낸 사용자의 SessionId
		String payload = message.getPayload();	//사용자가 보낸 메세지 내용
		
		System.out.println("payload >>> " +payload);
		
		dataMap = jsonToMap(payload);
		dataMap.put("senderId", senderId);
		dataMap.put("time", time);
		dataMap.put("masterStatus", masterStatus);
		dataMap.put("onlineList", onlineList);
		
		String receiverId = (String) dataMap.get("receiverId");
		
		System.out.println("final dataMap >>> " +dataMap);
		
		//send a message
		System.out.println("receiver session >>> " + userSession.get(receiverId));
		String msg = json.writeValueAsString(dataMap);
		
		if(userSession.get(receiverId) != null) {
			userSession.get(receiverId).sendMessage(new TextMessage(msg));	//send to receiver , master가 있을때만 보냄 
		}
		
		//send a message myself
		if(!senderId.equals(receiverId)) {
			dataMap.put("receiverId", senderId);
			msg = json.writeValueAsString(dataMap);
			session.sendMessage(new TextMessage(msg));	//send to myself
		}
	}
	
	//connection established
	//websocket에 session이 접속했을 때, 처리하는 메소드 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		//svae into session list
		String senderId = (String) session.getAttributes().get("sessionId");
		sessionList.add(session);
		System.out.println("sessionId >>> " + senderId);
		onlineList.add(senderId);
		userSession.put(senderId, session);
		
		//as master send message to all
		if(senderId.equals("master")) {
			TextMessage msg = new TextMessage(senderId + "님이 접속했습니다.");
			sendToAll(msg,senderId);
		}else {
			Map<String, Object> data = new HashMap<>();
			data.put("message", senderId + "님이 접속하셨습니다.");
			data.put("receiverId", "master");
			data.put("newOne", senderId);
			
			TextMessage msgToMaster = new TextMessage(json.writeValueAsString(data));
			handleMessage(session, msgToMaster);	
		}
		System.out.println(session + " client connected");
		
	}

	//connection close
	//websocket에 session이 접속을 해제했을 때, 처리하는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		String senderId = (String) session.getAttributes().get("sessionId");
		sessionList.remove(session);
		onlineList.remove(senderId);
		userSession.remove(senderId);
		
		//as master send to all
		if(senderId.equals("master")) {
			TextMessage msg = new TextMessage(senderId + " 님이 퇴장했습니다.");
			sendToAll(msg, senderId);
		}else {
			Map<String, Object> data = new HashMap<>();
			data.put("message", senderId + " 님이 퇴장하셨습니다.");
			data.put("receiverId", "master");
			data.put("outOne", "senderId");
			
			TextMessage msg = new TextMessage(json.writeValueAsString(data));
			handleMessage(session, msg);
		}
		
		System.out.println(session + "client disconnected");
	}
	
	//json string parsing to map
	public Map<String, Object> jsonToMap(String jsonString) throws JsonMappingException, JsonProcessingException{
		Map<String, Object> map =new HashMap<>();
		ObjectMapper jmapper = new ObjectMapper();
		map = jmapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){
			
		});
		
		return map;
	}
	
	private void sendToAll(TextMessage message, String senderId) throws Exception{
		// TODO Auto-generated method stub
		Map<String, Object> dataMap = new HashMap<>();
		
		//master status
		String masterStatus = null;
		if(userSession.containsKey("master")) {
			masterStatus = "online";
		}else {
			masterStatus = "offline";
		}
		
		//sending time
		LocalDateTime currentTime = LocalDateTime.now();
		String time = currentTime.format(DateTimeFormatter.ofPattern("hh:mm a E"));
		
		//message data
		String payload  = message.getPayload();
		
		System.out.println("payload >> "+ payload);
		
		dataMap.put("message", message.getPayload());
		dataMap.put("senderId", senderId);
		dataMap.put("time", time);
		dataMap.put("masterStatus", masterStatus);
		dataMap.put("onlineList", onlineList);	// user online status
		dataMap.put("newOne", "master");
		
		String receiverId = (String) dataMap.get("receiverId");
		
		System.out.println("final data Map >>> " +dataMap);
		
		//send a message
		System.out.println("receiver session >>> " + userSession.get(receiverId));
		
		//
		for(String r : userSession.keySet()) {
			dataMap.put("receiverId", r);
			String msg = json.writeValueAsString(dataMap);
			userSession.get(r).sendMessage(new TextMessage(msg));
		}
		
	}
	
	public void sendOnlineList(WebSocketSession session) throws IOException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("onlineList", onlineList);
		String list = json.writeValueAsString(map);
		
		log.info("map >>> " + map);
		session.sendMessage(new TextMessage(list));
	}
	
}
