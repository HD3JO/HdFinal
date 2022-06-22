package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.service.ProductService;

@Controller
public class ProductTestController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/detail")
	public String productDetail(@RequestParam String pcid, Model model) throws Exception {
		
		
		ProductDetailDTO detailDTO = productService.getProductDetail(pcid);
		List<ProductColorDTO> colorDTOs = productService.getProductColor(pcid);
		List<ProductSizeDTO> sizeDTOs = productService.getProductSize(pcid);
		
		System.out.println("넘어왔니 : " + colorDTOs.get(0).getPcid());
		
		
		model.addAttribute("pcid", pcid);
		model.addAttribute("detailDTO", detailDTO);
		model.addAttribute("colorDTOs", colorDTOs);
		model.addAttribute("sizeDTOs", sizeDTOs);
		
		return "productDetail";
	} 
	
	
	@RequestMapping(value="/test")
	public String Test(@RequestParam String pcid, Model model) throws Exception {
		
		model.addAttribute("pcid", pcid);
		return "dataTest";
	}
	
}
