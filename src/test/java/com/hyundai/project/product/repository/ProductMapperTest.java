package com.hyundai.project.product.repository;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.SQLException;

import com.hyundai.project.dto.ProductDetailDTO;



@SpringBootTest

public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void getProduct() {
		String pid = "CM2B0KOP301WBK";
		ProductDetailDTO productDetail = productMapper.getProductDetail(pid);
		
		System.out.println(productDetail.getPname());
	}
}