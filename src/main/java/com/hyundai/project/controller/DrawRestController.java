package com.hyundai.project.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.project.dto.DrawDTO;
import com.hyundai.project.service.DrawService;

@RestController
public class DrawRestController {
	
	@Autowired
	DrawService drawService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@PostMapping("/drawInsert")
	public ResponseEntity<String> drawInsert(Authentication authentication, @RequestBody DrawDTO drawDTO) throws Exception {						
		
		/* 
		 * 1) unique한 key값 생성  
		 *     - key로 사용할 uid 생성   
		 */
		ObjectMapper mapper = new ObjectMapper();
		
		UUID uid = UUID.randomUUID();	
		
		/*
		 * 2) 같은 제품에 대해 계정 당 한 번만 응모 가능하게 하는 기능
		 *     - redis에 있는 데이터들 탐색
		 *     - 세션의 email값과 map.get('email') 값이 일치하고, 
		 *       drawDTO의 psid 값과 map.get('psid')의 값이 일치하면 중복된 응모 내역이 있는 거므로 alert 출력
		 */
		// 로그인 정보 받아오기 위한 UserDetails 클래스 사용 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				
		// 현재 로그인 유저 이메일 
		String email = userDetails.getUsername();
		 
		 // redis에 있는 모든 키 값들 리스트 형태로 받아옴 
		 Set<String> redisKeys = redisTemplate.keys("*");
		 List<String> keysList = new ArrayList<>();
		 Iterator<String> keys = redisKeys.iterator();
		 while(keys.hasNext()) {
			 String data = keys.next();
			 keysList.add(data);
		 }
		 
		 // 현재 세션의 이메일, PSID와 redis의 value 값 비교해서 일치하는게 있으면 check = true 
		 Boolean check = false;		 
		 for(int i = 0; i < keysList.size(); i++) {
			 Object val = redisTemplate.opsForValue().get(String.valueOf(keysList.get(i)));
			 Map<String, Object> map = mapper.convertValue(val, Map.class);
			
			 if(((String)map.get("email")).equals(email) && ((String)map.get("psid")).equals(drawDTO.getPsid())) {
				 check = true;
				 break;
			 }			 
		 }
		 
		 // 중복되는게 없으면
		 if(check == false) {
			 	/* 
				 * 3) 마일리지 차감 
				 *     - 사용자가 사용한 마일리지 차감하는 기능
				 *     - Object 형태의 redis 데이터를 map 형태로 바꿔서 email과 pmileage값 얻어온 후 update
				 *     - 예외사항 : 사용자의 마일리지가 pmileage보다 모자라면 화면에서 alert 출력하도록 함
				 *                update 쿼리의 수행값이 1이면 update해도 마일리지가 0보다 큼, 그렇지 않으면 부족한 거
				 *                마일리지 부족한 경우 set 되어있던 값 delete
				 */ 	
			 	redisTemplate.opsForValue().set(String.valueOf(uid), drawDTO);
			 	
				Object val = redisTemplate.opsForValue().get(String.valueOf(uid));
				
				Map<String, Object> map = mapper.convertValue(val, Map.class);
				
				DrawDTO dto = new DrawDTO();
				dto.setEmail((String) map.get("email"));
				dto.setPmileage((Integer) map.get("pmileage")); 
				
				int result = drawService.updateMileage(dto);
				
				if(result == 1) 			
					return new ResponseEntity<>("Draw Success", HttpStatus.CREATED);
				else {					
					redisTemplate.opsForValue().getAndDelete(String.valueOf(uid));
					return new ResponseEntity<>("Not Enough Mileage", HttpStatus.CREATED);
				}
		   //중복이 있으면	 
		 } else {
			 redisTemplate.opsForValue().getAndDelete(String.valueOf(uid));
			 return new ResponseEntity<>("Duplicate Application", HttpStatus.CREATED);
		 }
	}
}
