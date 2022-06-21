package com.hyundai.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hyundai.project.dto.ProductViewDTO;
import com.hyundai.project.service.ProductListService;

@Controller
//@RequestMapping("/list/*")
public class ProductListController {
	
	@Autowired
	private ProductListService productListService;
	
	@RequestMapping(value="/productList/{cateNo}", method=RequestMethod.GET)
	public String productList(Model model, @PathVariable String cateNo) throws Exception{
		
		List<ProductViewDTO> productList = productListService.productList(cateNo);;
		
		model.addAttribute("productList", productList);
		
		return "productList";
	}
}
