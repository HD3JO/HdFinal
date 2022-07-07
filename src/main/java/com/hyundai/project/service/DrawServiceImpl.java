package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.DrawDTO;
import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.product.repository.DrawMapper;

@Service
public class DrawServiceImpl implements DrawService {

	@Autowired
	DrawMapper drawMapper; 
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public List<DrawListDTO> getDrawList() throws Exception {		
		
		return drawMapper.getDrawList();
	}

	@Override
	public DrawListDTO getDrawDetail(String psid) throws Exception {

		return drawMapper.getDrawDetail(psid);
	}

	@Override
	public int updateMileage(DrawDTO drawDTO) throws Exception {
				
		return drawMapper.updateMileage(drawDTO);
	}
}
