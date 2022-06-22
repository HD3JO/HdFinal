package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductListDTO;
import com.hyundai.project.service.ProductService;

@Controller
//@RequestMapping("/list/*")
public class ProductListController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/productList", method=RequestMethod.GET)
	public String productList(Model model, @RequestParam("depth1name")String depth1name,
										   @RequestParam("depth2name")String depth2name,
										   @RequestParam("depth3name")String depth3name) throws Exception{
		
		
		List<ProductListDTO> productList = productService.getProductList(depth1name, depth2name, depth3name);
		System.out.println(productList);
		model.addAttribute("productList", productList);
		
		return "productList";
	}
	
	@RequestMapping(value="/productImg/{pcid}", method=RequestMethod.POST)
	public ResponseEntity<ProductColorDTO> productImg(@PathVariable("pcid") String pcid){
		System.out.println(pcid);
		ProductColorDTO dto = productService.getProductImgByPcid(pcid);
		System.out.println(dto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
