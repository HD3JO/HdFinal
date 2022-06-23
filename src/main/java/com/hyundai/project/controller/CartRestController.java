package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CartRestController {
	
	@Autowired
	CartService service;
	
	// 사용자가 담은 장바구니 목록을 조회
	@PostMapping(value = "/getCartList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CartDTO>> getCartList(@RequestBody CartDTO dto) throws Exception {
		log.info("getCartList Controller.......................");
		
		return new ResponseEntity<>(service.getCartList(dto.getEmail()), HttpStatus.OK);
	}
	
	// 상품 장바구니에 담기
	@PostMapping(value="/cartInsert")
	public ResponseEntity<String> cartInsert(@RequestBody CartDTO dto){
		ResponseEntity<String> list = null;
		
		try {
			service.cartInsert(dto);
			list = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ResponseEntity<String>(
								e.getMessage(), HttpStatus.BAD_REQUEST);			
		}
		return list;
	}
	
	// 장바구니 수량 변경
	@PostMapping(value="/cartUpdate")
	public ResponseEntity<CartDTO> cartUpdate(@RequestBody CartDTO dto) throws Exception{
		int rows = service.cartUpdate(dto);
		
		if(rows != -1) {
			return new ResponseEntity<CartDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CartDTO>(dto, HttpStatus.OK);
	}
	
	// 장바구니 삭제
	@PostMapping(value="/cartDelete")
	public ResponseEntity<String> cartDelete(@RequestBody CartDTO cartList){
		ResponseEntity<String> delete = null;
		try {
			service.cartDelete(cartList);
			delete = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			delete = new ResponseEntity<String>(
								e.getMessage(), HttpStatus.BAD_REQUEST);			
		}
		return delete;
	}
	
	// 체크박스 선택된 장바구니 상품 일괄 삭제
	@PostMapping(value="/selectDelete")
	public String deleteCart(@RequestBody List<CartDTO> cartList, Model model, Authentication authentication) throws Exception {
		log.info("Called deleteCart");
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		if(userDetails == null) 
			return "redirect:/customLogin";
		String email = userDetails.getUsername();;
		
		for(CartDTO dto : cartList) {
			log.info(dto.toString());
			dto.setEmail(email);
		}
		int rows = service.selectDelete(cartList);
		
		cartList = service.getCartList(email);
		
		model.addAttribute("cartList", cartList);
		
		return "redirect:/cart/cartList";
	}
}
