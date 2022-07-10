package com.hyundai.project.service;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.OrderCompleteDTO;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */
@Service("MessageService")
@Component
public class MessageService {

	 String api_key = "NCSHHTLE8HMYWI1D";
     String api_secret = "HPHA1DNAQKEQFMR4NOBYBQHUEUEF0EJ2";
     Message coolsms = new Message(api_key, api_secret);
     
     @Autowired(required=false)
     private List<OrderCompleteDTO> completeList;
    
     @Autowired(required=false)
     public void sendMessage(List<OrderCompleteDTO> completeList) {
    	 
		Message coolsms = new Message(api_key, api_secret);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", completeList.get(0).getPhone());
		params.put("from", "01068884025");
		params.put("type", "SMS");
		params.put("text", "<h1>한섬3조</h1>"+"<a href='http://localhost:80/order/orderStatus?oid=" + completeList.get(0).getOid() +"'>주문확인</a>");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}

}