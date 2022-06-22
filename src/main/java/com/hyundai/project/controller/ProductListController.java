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
	public String productList(Model model, @RequestParam(value="depth1name")String depth1name,
										   @RequestParam(value="depth2name",required = false)String depth2name,
										   @RequestParam(value="depth3name",required = false)String depth3name,
										   @RequestParam(value="pagenum", defaultValue="1")int pagenum,
										   @RequestParam(value="pname", required = false) String pname) throws Exception{
	System.out.println(pname + "@@@@@@@@@@@");
		List<ProductListDTO> productList = productService.getProductList(depth1name, depth2name, depth3name, pagenum,pname);
		System.out.println("depth1name " +depth1name);
		if("".equals(depth2name)) depth2name = null;
		if("".equals(depth3name)) depth3name = null;
		model.addAttribute("productList", productList);
		int totalcount = productService.getProductCount(depth1name, depth2name, depth3name, pagenum, pname);
		model.addAttribute("totalcount", totalcount);
		model.addAttribute("endpagenum", (totalcount/12)+1);
		model.addAttribute("depth1name",depth1name);
		model.addAttribute("depth2name",depth2name);
		model.addAttribute("depth3name",depth3name);
		model.addAttribute("presentpagenum", pagenum);
		model.addAttribute("pname", pname);
		
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
