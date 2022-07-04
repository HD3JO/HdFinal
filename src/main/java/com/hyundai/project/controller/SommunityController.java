package com.hyundai.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sommunity")
@Controller
public class SommunityController {
	
	@GetMapping("/home")
	public String home() {
		
		return "sommunity/index";
	}
	
	@GetMapping("/brand")
	public String brandBoard(@RequestParam("bname") String bname, Model model) {
		model.addAttribute("bname", bname);
		return "sommunity/board";
	}
	
	@GetMapping("/write")
	public String writeform(@RequestParam("bname") String bname, Model model) {
		model.addAttribute("bname", bname);
		return "sommunity/write_form";
	}
	
}
