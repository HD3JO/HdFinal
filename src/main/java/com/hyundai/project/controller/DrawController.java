package com.hyundai.project.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.service.DrawService;

@Controller
@RequestMapping("/draw")
public class DrawController {
	
	@Autowired
	DrawService drawService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@GetMapping("/drawList")
	public String index(Model model) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<DrawListDTO> drawList = drawService.getDrawList();
		
		Set<String> redisKeys = redisTemplate.keys("*");
		Iterator<String> keys = redisKeys.iterator();
		List<String> keysList = new ArrayList<>();
		List<Integer> cntByPsidList = new ArrayList<>();
		int cntByPsid = 0;
		
		// redis에 있는 모든 키 값들 리스트 형태로 받아옴
		while(keys.hasNext()) {
			String data = keys.next();
			keysList.add(data);
		}
		
		// 드로우 상품 별 응모자 수
		for(int i = 0; i < drawList.size(); i++) {						 
			 for(int j = 0; j < keysList.size(); j++) {
				 Object val = redisTemplate.opsForValue().get(String.valueOf(keysList.get(j)));
				 Map<String, Object> map = mapper.convertValue(val, Map.class);
				 
				 if(((String)map.get("psid")).equals(drawList.get(i).getPsid())) {
					 cntByPsid++;
				 }
			 }
			 cntByPsidList.add(cntByPsid);
			 drawList.get(i).setCntByPsid(cntByPsid);
			 cntByPsid = 0;
		 }
		
		model.addAttribute("drawList", drawList);
		
		return "draw/drawList";
	}
	
	@GetMapping("/drawDetail")
	public String drawDetail(@RequestParam String psid, Model model) throws Exception {
		
		DrawListDTO drawDetail = drawService.getDrawDetail(psid);
		
		model.addAttribute("drawDetail", drawDetail);
		
		return "draw/drawDetail";
	}
	
	@GetMapping("/drawOrder")
	public String drawOrder(@RequestParam String email, @RequestParam String psid, Model model) throws Exception {
		
		MemberDTO dto = drawService.getWinMember(email);		
		
		model.addAttribute("winMember", dto);
		model.addAttribute("psid", psid);
		
		return "draw/drawOrder";
	}
}
