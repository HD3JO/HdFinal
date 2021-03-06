package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.service.MemberService;

@Controller
public class HomeController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/header", method = RequestMethod.GET)
	public String headerGet() {
		System.out.println("called Header");
		return "include/header";
	}
	
	@RequestMapping(value="/customLogin", method = RequestMethod.GET)
	public String login() {
		System.out.println("called login page");
		return "loginForm";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register() {
		System.out.println("called register");
		return "register";
	}
	
	@RequestMapping(value="/complete", method= RequestMethod.GET)
	public String complete() {
		System.out.println("called complete");
		return "complete";
	}
}
