package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/drawList")
	public String index(Model model) throws Exception {
		
		List<DrawListDTO> drawList = drawService.getDrawList();
		
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
