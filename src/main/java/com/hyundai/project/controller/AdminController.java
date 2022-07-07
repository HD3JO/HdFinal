package com.hyundai.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyundai.project.dto.AdminProductDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.ProductCommonDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductStockDTO;
import com.hyundai.project.service.AdminProductService;
import com.hyundai.project.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private AdminProductService adminProductService;
	
	
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
	
	@RequestMapping(value="/producttables", method=RequestMethod.GET)
	public String producttablesGet(Model model) throws Exception{
		model.addAttribute("brandlist", adminProductService.getBrand());
		
		return "admin/producttables";
	}
	
	@RequestMapping(value="/producttables", method=RequestMethod.POST)
	public String producttablesGet(Model model, AdminProductDTO adminProductCommon) throws Exception{
		System.out.println(adminProductCommon+"@@@@@@@@@@@@@@@");
		model.addAttribute("selectedBname",adminProductCommon.getBname());
		model.addAttribute("selectedPname",adminProductCommon.getPname());
		
		model.addAttribute("brandlist", adminProductService.getBrand());
		List<AdminProductDTO> list = adminProductService.getProductCommon(adminProductCommon);
		model.addAttribute("productlist", list);
		return "admin/producttables";
	}
	
	@RequestMapping(value="/productDetail", method=RequestMethod.POST)
	public ResponseEntity<List<ProductDetailDTO>> productDetailPost(@RequestBody ProductCommonDTO productCommonDTO) throws Exception{
		List<ProductDetailDTO> list = adminProductService.getProductDetail(productCommonDTO.getPid());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateProductStock", method=RequestMethod.POST)
	public ResponseEntity<String> updateProductStock(@RequestBody List<ProductStockDTO> productStockDTOList) throws Exception{
		for(ProductStockDTO a : productStockDTOList) {
			System.out.println(a);
		}
		adminProductService.updateProductStock(productStockDTOList);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/chat", method=RequestMethod.GET)
	public String chat(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("sessionId", "master");
		
		return "admin/chat";
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