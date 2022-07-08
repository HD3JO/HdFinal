package com.hyundai.project.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.project.dto.DrawListDTO;
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
		
		List<DrawListDTO> drawList = drawService.getDrawList();
		
		Set<String> redisKeys = redisTemplate.keys("*");
		Iterator<String> keys = redisKeys.iterator();
		List<String> keysList = new ArrayList<>();
		int cnt = 0;
		
		// redis에 있는 모든 키 값들 리스트 형태로 받아옴
		while(keys.hasNext()) {
			String data = keys.next();			
			cnt++;
		}
		
		model.addAttribute("totalCount",  cnt);
		model.addAttribute("drawList", drawList);
		
		return "/draw/drawList";
	}
	
	@GetMapping("/drawDetail")
	public String drawDetail(@RequestParam String psid, Model model) throws Exception {
		
		DrawListDTO drawDetail = drawService.getDrawDetail(psid);
		
		model.addAttribute("drawDetail", drawDetail);
		
		return "/draw/drawDetail";
	}
}
