package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.product.repository.ProductMapper;

public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductMapper productmapper;
	
	@Override
	public ProductDetailDTO getProductDetail(String pcid) throws Exception {
		// TODO Auto-generated method stub
		
		return productmapper.getProductDetail(pcid);
	}

}
