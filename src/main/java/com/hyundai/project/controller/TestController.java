package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	TestService service;
	
	@RequestMapping("/db1")
	public @ResponseBody String db1() throws Exception {
		System.out.println("db1 controller...................");
		return service.user();
	}
	
	@RequestMapping("/test")
	public @ResponseBody String test() throws Exception {
		System.out.println("test.......................");
		return "test";
	}
}
