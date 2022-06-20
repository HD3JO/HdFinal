package com.hyundai.project.product.repository;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dto.ProductDetailDTO;



@SpringBootTest
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void getProductDetail() {
		ProductDetailDTO productDetail = productMapper.getProductDetail("CM2B0KOP301WBK");
		
		System.out.println(productDetail.getPname());
	}
}