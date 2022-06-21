package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.ProductAddSizeDTO;
import com.hyundai.project.dto.ProductViewDTO;
import com.hyundai.project.product.repository.ProductListMapper;
import com.hyundai.project.product.repository.ProductMapper;

@Service
public class ProductListServiceImpl implements ProductListService {

	@Autowired
	private ProductListMapper productListMapper;
	
	
	@Override
	public List<ProductViewDTO> productList(int cateNo) throws Exception {
		System.out.println("ProductList Service start.....................");
		ProductViewDTO productViewDTO = new ProductViewDTO();
		return productListMapper.productList(cateNo);
	}

	@Override
	public List<ProductAddSizeDTO> productSize(String pid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
