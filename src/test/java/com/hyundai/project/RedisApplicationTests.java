package com.hyundai.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.hyundai.project.dto.DrawListDTO;

@SpringBootTest
class RedisApplicationTests {
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	void redisConnectionTest() {
		DrawListDTO dto = new DrawListDTO();
		
		dto.setPsid("test1");
		dto.setPmileage(12000);
		
		redisTemplate.opsForValue().set("key", dto);
	}
}
