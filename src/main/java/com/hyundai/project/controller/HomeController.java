package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/header", method = RequestMethod.GET)
	public String headerGet() {
		System.out.println("called Header");
		return "include/header";
	}
}
