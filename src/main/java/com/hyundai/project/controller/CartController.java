package com.hyundai.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@GetMapping("/cartList")
	public String cartList(Authentication authentication, Model model) {
		
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    model.addAttribute("email", userDetails.getUsername());
		
		return "cart/cartList";
	}
}
