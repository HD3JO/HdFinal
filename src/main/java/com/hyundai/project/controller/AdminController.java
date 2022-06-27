package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping(value="/blank", method=RequestMethod.GET)
	public String blank() {
		return "admin/blank";
	}
	@RequestMapping(value="/buttons", method=RequestMethod.GET)
	public String buttons() {
		return "admin/buttons";
	}
	@RequestMapping(value="/cards", method=RequestMethod.GET)
	public String cards() {
		return "admin/cards";
	}
	@RequestMapping(value="/charts", method=RequestMethod.GET)
	public String charts() {
		return "admin/charts";
	}
	@RequestMapping(value="/forgotpassword", method=RequestMethod.GET)
	public String forgotpassowrd() {
		return "admin/forgot-password";
	}
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "admin/register";
	}
	@RequestMapping(value="/membertables", method=RequestMethod.GET)
	public String tables(Model model) throws Exception {
		List<MemberDTO> list = memberService.selectUserAdmin(new MemberDTO());
		model.addAttribute("memberList", list);
		System.out.println(list);
		return "admin/tables";
	}
	@RequestMapping(value="/membertables", method=RequestMethod.POST)
	public String membertables(MemberDTO memberDTO, Model model) throws Exception{
		System.out.println(memberDTO);
		List<MemberDTO> list = memberService.selectUserAdmin(memberDTO);
		model.addAttribute("memberList", list);
		System.out.println(list);
		return "admin/tables";
	}
	
	@RequestMapping(value="/modifyMember", method=RequestMethod.POST)
	public ResponseEntity<String> modifyMember(@RequestBody List<MemberDTO> memberList) throws Exception{
		System.out.println(memberList);
		memberService.updateMemeberAdmin(memberList);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}

	@RequestMapping(value="/utilities-animation", method=RequestMethod.GET)
	public String utilitiesAnimation() {
		return "admin//utilities-animation";
	}
	@RequestMapping(value="/utilities-border", method=RequestMethod.GET)
	public String utilitiesBorder() {
		return "admin/utilities-border";
	}
	@RequestMapping(value="/utilities-color", method=RequestMethod.GET)
	public String utilitiesColor() {
		return "admin/utilities-color";
	}
	@RequestMapping(value="/utilities-other", method=RequestMethod.GET)
	public String utilitiesOther() {
		return "admin/utilities-other";
	}
}