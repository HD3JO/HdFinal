package com.hyundai.project.product.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dto.CategoryDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductImgDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.dto.ProductViewDTO;

@SpringBootTest
public class PoductMapperTest {
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void getProductListByCategoryTest() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDepth1name("KIDS");
		
		List<ProductViewDTO> list=  productMapper.getProductListByCategory(categoryDTO);
		for(ProductViewDTO a : list) {
			System.out.println(a);
		}
	}
	
	@Test
	public void getProductImgByPid() {
		List<ProductImgDTO> list = productMapper.getProductImgByPid("CM2B4KOP956C");
		for(ProductImgDTO a : list) {
			System.out.println(a);
		}
	}
	
	@Test
	public void getProductsizeByPidTest() {
		List<ProductSizeDTO> list = productMapper.getProductsizeByPid("CM2B4KOP956C");
		for(ProductSizeDTO a:  list) {
			System.out.println(a);
		}
	}
	@Test
	public void getProductColorCodeByPidTest() {
		List<ProductColorDTO> list= productMapper.getProductColorCodeByPid("CM2B4KOP956C");
		for(ProductColorDTO a : list) {
			System.out.println(a);
		}
	}
}
