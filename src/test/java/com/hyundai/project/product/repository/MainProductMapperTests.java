package com.hyundai.project.product.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dto.NewProductDTO;

@SpringBootTest
public class MainProductMapperTests {

	@Autowired
	private MainProductMapper newProductMapper;
	
	@Test
	public void getNewProductTest() throws Exception{
		NewProductDTO product = new NewProductDTO();
		
		System.out.println(newProductMapper.getNewProduct("WOMEN"));
	}
}
