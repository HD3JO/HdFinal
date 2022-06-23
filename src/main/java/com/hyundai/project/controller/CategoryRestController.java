package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.CategoryDTO;
import com.hyundai.project.service.ProductService;

@Controller
public class CategoryRestController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/getCategoryList")
	public ResponseEntity<List<CategoryDTO>> getCategoryList(){
		return new ResponseEntity<>(productService.getCategoryList(),HttpStatus.OK);
	}
}
