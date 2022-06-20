package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRole;
import com.hyundai.project.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberRestController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/isduplemail", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> isdulpemail(@RequestBody String email) throws Exception{
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@MemberController isdulpeamil : " +email);
		MemberDTO memberDTO = memberService.selectOneUser(email);
		if(memberDTO == null) {
			return new ResponseEntity<String>("SUCEESS", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAILURE", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody MemberDTO memberDTO) {
		
		System.out.println("register called : " + memberDTO);
		//멤버 기본 세팅
		memberDTO.setRole(MemberRole.USER);
		memberDTO.setEnable(1);
		memberDTO.setChannel("c");
		memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
		System.out.println("after called : " + memberDTO);
		
		try {
			int count = memberService.insertMember(memberDTO);
			System.out.println("insert data count : " + count);
			if(count == -1) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("FAILURE", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE", HttpStatus.BAD_REQUEST);
	}
}
