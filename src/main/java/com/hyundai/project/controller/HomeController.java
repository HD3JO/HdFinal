package com.hyundai.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(value="/customLogin", method = RequestMethod.GET)
	public String login() {
		System.out.println("called login page");
		return "loginForm";
	}
	
	@RequestMapping(value="/member", method=RequestMethod.GET)
	public ResponseEntity<String> member(){
		return new ResponseEntity<>("This is member", HttpStatus.OK);
	}
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ResponseEntity<String> admin(){
		return new ResponseEntity<>("This is admin", HttpStatus.OK);
	}
}
