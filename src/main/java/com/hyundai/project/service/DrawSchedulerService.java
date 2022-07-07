package com.hyundai.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.DrawWinDTO;
import com.hyundai.project.product.repository.DrawMapper;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@Service
public class DrawSchedulerService {
	@Autowired
	DrawMapper drawMapper; 
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Scheduled(fixedDelay = 5000)
	public List<DrawWinDTO> getWinning() throws Exception {
			List<DrawWinDTO> winList = new ArrayList<>();
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
				
				 for(int j = 0; j < keysList.size(); j++) {
					 Object val = redisTemplate.opsForValue().get(String.valueOf(keysList.get(j)));
					 Map<String, Object> map = mapper.convertValue(val, Map.class);					 
					 String psidDB = (String)map.get("psid");
					 
					 if(psid.equals(psidDB)) { 
						 keysListByPsid.add((String)keysList.get(j));
					 }						 					 					 
				 }				 
				 
				 if(keysListByPsid.size() != 0 ) {
					 //log.info("해당하는 key : "+keysListByPsid);
					 Collections.shuffle(keysListByPsid);	
					 Object winVal = redisTemplate.opsForValue().get(String.valueOf(keysListByPsid.get(0)));
					 Map<String, Object> winMap = mapper.convertValue(winVal, Map.class);
					 
					 winDTO.setEmail((String)winMap.get("email"));
					 winDTO.setPsid((String)winMap.get("psid"));
					 winDTO.setPcid((String)winMap.get("pcid"));
					 winDTO.setPid((String)winMap.get("pid"));
					 
					 winList.add(winDTO);				
					 
					 //log.info("당첨자 이메일은!!! "+(String)winMap.get("email"));
					 keysListByPsid.clear();
					 //redis에 있는 데이터 지워주는거 추가
				 } else if(keysListByPsid.size() == 0) {
					 //log.info("해당 상품에 대한 응모 내역이 없습니다.");
				 }
			 }		
			 return winList;
		}
}