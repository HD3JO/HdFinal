package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.ProductAddSizeDTO;
import com.hyundai.project.dto.ProductViewDTO;

public interface ProductListService {

	public List<ProductViewDTO> productList(int cateNo) throws Exception;
	
	public List<ProductAddSizeDTO> productSize(String pid) throws Exception;
}
