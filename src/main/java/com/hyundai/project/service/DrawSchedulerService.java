package com.hyundai.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.DrawWinDTO;
import com.hyundai.project.product.repository.DrawMapper;

import lombok.extern.log4j.Log4j;

@Component
@Service
@Log4j
public class DrawSchedulerService {
	@Autowired
	DrawMapper drawMapper; 
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private JavaMailSender emailSender;
	
	// 당첨자 추첨하고 당첨자에게 이메일 전송하는 메서드
	//@Scheduled(cron ="0 0/20 * * * ?")
	//@Scheduled(fixedDelay = 60000)
	public DrawWinDTO getWinning() throws Exception {		
			DrawWinDTO winDTO = new DrawWinDTO();
			
			ObjectMapper mapper = new ObjectMapper();
			List<DrawListDTO> dto = drawMapper.getDrawList();
			 
			Set<String> redisKeys = redisTemplate.keys("*");
			List<String> keysList = new ArrayList<>();
			List<String> keysListByPsid = new ArrayList<>();
			Iterator<String> keys = redisKeys.iterator();
			
			// redis에 있는 모든 키 값들 리스트 형태로 받아옴
			while(keys.hasNext()) {
				String data = keys.next();
				keysList.add(data);
			}
			
			// 받아온 키 값들로부터 value값 받아옴 
			for(int i = 0; i < dto.size(); i++) {
				String psid = dto.get(i).getPsid();
				String pname = dto.get(i).getPname();
				//log.info(pname+" 제품에 해당하는 응모 내역 찾기");
				
				 // 등록된 제품의 psid에 대해 응모 내역이 있으면 keysListByPsid에 넣어준다.
				 for(int j = 0; j < keysList.size(); j++) {
					 Object val = redisTemplate.opsForValue().get(String.valueOf(keysList.get(j)));
					 Map<String, Object> map = mapper.convertValue(val, Map.class);					 
					 String psidDB = (String)map.get("psid");
					 
					 if(psid.equals(psidDB)) { 
						 keysListByPsid.add((String)keysList.get(j));
					 }						 					 					 
				 }				 
				 
				 // keysListByPsid의 사이즈가 0이 아니면 해당 제품에 대한 응모 내역이 존재 
				 if(keysListByPsid.size() != 0 ) {
					 // log.info("해당하는 key : "+keysListByPsid);
					 // 응모 내역을 섞어서 무작위로 배열되도록
					 Collections.shuffle(keysListByPsid);
					 
					 // 배열의 첫 인덱스 value 값(당첨자 정보) 
					 Object winVal = redisTemplate.opsForValue().get(String.valueOf(keysListByPsid.get(0)));
					 Map<String, Object> winMap = mapper.convertValue(winVal, Map.class);
					 
					 // 당첨자의 전화번호
					 String phone = drawMapper.getPhoneByEmail((String)winMap.get("email")).getPhone();					 				 
					 
					 // winDTO에 당첨자 정보 세팅
					 winDTO.setEmail((String)winMap.get("email"));
					 winDTO.setPsid((String)winMap.get("psid"));
					 winDTO.setPcid((String)winMap.get("pcid"));
					 winDTO.setPid((String)winMap.get("pid"));
					 winDTO.setPhone(phone);
					 			
					 //System.out.println(winDTO);					
					 
					 // 당첨자 오라클DB에 insert
					 drawMapper.insertWinDraw(winDTO);
					 
					 // 당첨자 번호로 문자메세지 전송 					 
					 sendWinEmail(pname, winDTO.getEmail(), winDTO.getPsid());
					 
					 // dto 비워주기, 다음 제품 추첨을 위해
					 winDTO = new DrawWinDTO();					 					 
					 keysListByPsid.clear();				
					 					  					 
				 } else if(keysListByPsid.size() == 0) {
					 log.info("해당 상품에 대한 응모 내역이 없습니다.");
				 }
			 }	
			
			 // 추첨이 끝나면 Redis에 있는 모든 데이터 삭제
			 redisTemplate.keys("*").stream().forEach(k-> {
				 redisTemplate.delete(k);
			 });
			 
			 return winDTO;
		}
	
	//메세지 전송하는 기능
//	public void sendWinSms(String phone, String pname, String email, String psid){
//		
//		String api_key = "NCSC2U4IA5IWMONC";
//		String api_secret = "QUK0ICWXGL2KWIS0L3NOVKLDPAJTPJX4";
//		
//		Message winSms = new Message(api_key, api_secret);
//		HashMap<String, String> params = new HashMap<String, String>();
//		
//		params.put("to", phone);
//		params.put("from", "01053495253");
//		params.put("type", "SMS");
//		params.put("text", "<THE HANDSOME DRAW>\n"
//				+ "<a href='http://localhost/draw/drawOrder?email=" + email + "&psid=" + psid + "'>배송지 입력</a>");		
//		params.put("app_version", "test app 1.2");
//
//		try {
//			JSONObject obj = (JSONObject) winSms.send(params);
//			System.out.println(obj.toString());
//		} catch (CoolsmsException e) {
//			System.out.println(e.getMessage());
//			System.out.println(e.getCode());
//		}
//	}
	
	public void sendWinEmail(String pname, String email, String psid) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("jyshustar@gmail.com");
		message.setTo(email);
		message.setSubject("<THE HANDSOME DRAW> 당첨안내입니다!");
		message.setText("<THE HANDSOME DRAW>\n응모하신" + pname + " 제품에 당첨되었습니다!!\n"
				+ "아래 링크에 접속하여 배송지를 입력해주시기 바랍니다.\n"
				+ "http://4stmen.kro.kr:40300/draw/drawOrder?email=" + email + "&psid=" + psid);
		emailSender.send(message);
	}

}
