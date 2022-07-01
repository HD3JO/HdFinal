package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRole;
import com.hyundai.project.dto.MemberUserDetails;
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
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(Model model) throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		model.addAttribute("mypage", memberDTO);
		return "mypage";
	}
	
	@RequestMapping(value="/modify", method= RequestMethod.GET)
	public String modify(Model model, Authentication authentication) throws Exception {
		MemberUserDetails authDTO = (MemberUserDetails) authentication.getPrincipal();
		MemberDTO dto = memberService.selectOneUser(authDTO.getEmail());
		String phone = dto.getPhone();	//01011112222;
		String hp1 = phone.substring(0, 3);
		String hp2 = phone.substring(3, 7);
		String hp3 = phone.substring(7, 11);
		model.addAttribute("hp1", hp1);
		model.addAttribute("hp2", hp2);
		model.addAttribute("hp3", hp3);
		return "modify";
	}
	
	@RequestMapping(value="/modify", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modify(@RequestBody MemberDTO memberDTO) throws Exception {
		System.out.println(memberDTO);
		memberDTO = memberService.updateMember(memberDTO);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@RequestMapping(value="/mygrade", method=RequestMethod.GET)
	public String mygrade() {
		return "mygrade";
	}
	
//	@RequestMapping(value="/myreview", method=RequestMethod.GET)
//	public String myreview(Model model, Authentication authentication) throws Exception {
//		MemberUserDetails authDTO = (MemberUserDetails) authentication.getPrincipal();
//		MemberDTO dto = memberService.selectOneUser(authDTO.getEmail());
//		List<OrderListDTO> orderListDTO = orderService.selectOrderList(dto);
//		return "myreview";
//	}
	
//	@RequestMapping(value="/myreview", method=RequestMethod.POST)
//	public List<OrderListDTO> selectOrderList(@RequestBody OrderListDTO orderListDTO) throws Exception{
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+orderListDTO);
//		return orderService.selectOrderList(email);
//	}
	
	@RequestMapping(value="/mypoint", method=RequestMethod.GET)
	public String mypoint() {
		return "mypoint";
	}
	
	@RequestMapping(value="/secession", method=RequestMethod.POST)
	public ResponseEntity<String> secession(Authentication authentication) throws Exception{
		MemberUserDetails dto = (MemberUserDetails)authentication.getPrincipal();
		String email = dto.getEmail();
		memberService.secession(email);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
}
