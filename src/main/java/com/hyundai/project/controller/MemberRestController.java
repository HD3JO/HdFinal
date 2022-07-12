package com.hyundai.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRole;
import com.hyundai.project.dto.MemberUserDetails;
import com.hyundai.project.dto.OrderListDTO;
import com.hyundai.project.service.MemberService;
import com.hyundai.project.service.OrderService;
import com.hyundai.project.service.ReviewService;

@Controller
@RequestMapping("/member/*")
public class MemberRestController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ReviewService reviewService;
	
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
	public String mypage(Model model, Authentication authentication) throws Exception {
		if(authentication == null) {
			return "redirect:/customLogin";
		}
		MemberUserDetails authDTO = (MemberUserDetails) authentication.getPrincipal();
		MemberDTO memberDTO = memberService.selectOneUser(authDTO.getEmail());
		List<OrderListDTO> orderList = orderService.selectOrderListByOneMonth(authDTO.getEmail());
		int reviewcount = reviewService.reviewcount(authDTO.getEmail());
		String name = memberDTO.getName();
		String grade = memberDTO.getGrade();
		model.addAttribute("reviewcount", reviewcount);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("name", name);
		model.addAttribute("orderList", orderList);
		model.addAttribute("grade", grade);
		return "mypage";
	}
	
	@RequestMapping(value="/modify", method= RequestMethod.GET)
	public String modify(Model model, Authentication authentication) throws Exception {
		if(authentication == null) {
			return "redirect:/customLogin";
		}
		MemberUserDetails authDTO = (MemberUserDetails) authentication.getPrincipal();
		MemberDTO dto = memberService.selectOneUser(authDTO.getEmail());
		String password = dto.getPassword();
		String phone = dto.getPhone();
		String hp1 = phone.substring(0, 3);
		String hp2 = phone.substring(3, 7);
		String hp3 = phone.substring(7, 11);
		String marketingemail = dto.getMarketingemail();
		String marketingsms = dto.getMarketingsms();
		model.addAttribute("hp1", hp1);
		model.addAttribute("hp2", hp2);
		model.addAttribute("hp3", hp3);
		model.addAttribute("marketingemail", marketingemail);
		model.addAttribute("marketingsms", marketingsms);
		model.addAttribute("password", password);
		return "modify";
	}
	
	@RequestMapping(value="/modify", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modify(@RequestBody MemberDTO memberDTO) throws Exception {
		System.out.println(memberDTO +"here1");
		if(!"".equals(memberDTO.getPassword())) {
			String password = passwordEncoder.encode(memberDTO.getPassword());
			memberDTO.setPassword(password);
		}
		memberService.updateMember(memberDTO);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@RequestMapping(value="/myorder", method=RequestMethod.GET)
	public String myorder(Model model, Authentication authentication) throws Exception {
		if(authentication == null) {
			return "redirect:/customLogin";
		}
		MemberUserDetails authDTO = (MemberUserDetails) authentication.getPrincipal();
		List<OrderListDTO> orderList = orderService.selectOrderList(authDTO.getEmail());
		model.addAttribute("orderList", orderList);
		return "myorder";
	}
	
	@RequestMapping(value="/myreview", method=RequestMethod.GET)
	public String myreview(Model model, Authentication authentication) throws Exception {
		if(authentication == null) {
			return "redirect:/customLogin";
		}
		
		MemberUserDetails authDTO = (MemberUserDetails) authentication.getPrincipal();
		List<OrderListDTO> orderList = orderService.selectOrderList(authDTO.getEmail());
		String pcid = reviewService.reviewCheck(authDTO.getEmail());
		model.addAttribute("orderList", orderList);
		model.addAttribute("pcid", pcid);
		return "myreview";
	}
	
	@RequestMapping(value="/secession", method=RequestMethod.POST)
	public ResponseEntity<String> secession(Authentication authentication) throws Exception{
		
		MemberUserDetails dto = (MemberUserDetails)authentication.getPrincipal();
		String email = dto.getEmail();
		memberService.secession(email);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	@GetMapping("/mydraw")
	public String mydraw(Authentication authentication, Model model) throws Exception{
		
		// 로그인 정보 받아오기 위한 UserDetails 클래스 사용 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		// 현재 로그인 유저 이메일 
		String email = userDetails.getUsername();
		
		List<DrawListDTO> dto = new ArrayList<>();
		
		dto = memberService.getMyDrawList(email);
		
		model.addAttribute("myList", dto);
		
		return "mydraw";
	}

}
