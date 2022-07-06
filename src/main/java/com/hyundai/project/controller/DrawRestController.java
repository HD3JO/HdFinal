package com.hyundai.project.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.project.dto.DrawDTO;
import com.hyundai.project.service.DrawService;

@RestController
public class DrawRestController {
	
	@Autowired
	private DrawService drawService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@PostMapping("/drawInsert")
	public ResponseEntity<DrawDTO> drawInsert(@RequestBody DrawDTO drawDTO) throws Exception {						
		
		/* 
		 * 1. uid 랜덤으로 생성하고 key로 설정, drawDTO Object형태로 value로 set  
		 * 2. value를 map 타입으로 변환 
		 */
		ObjectMapper mapper = new ObjectMapper();
		
		UUID uid = UUID.randomUUID();
		
		redisTemplate.opsForValue().set(String.valueOf(uid), drawDTO);
		
		Object val = redisTemplate.opsForValue().get(String.valueOf(uid));
		
		Map<String, Object> map = mapper.convertValue(val, Map.class);
		
		// 마일리지 업데이트
		DrawDTO dto = new DrawDTO();
		dto.setEmail((String) map.get("email"));
		dto.setPmileage((Integer) map.get("pmileage")); 
		
		drawService.updateMileage(dto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
