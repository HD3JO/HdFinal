package com.hyundai.project.product.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dto.AdminProductDTO;
import com.hyundai.project.dto.BrandDTO;
import com.hyundai.project.dto.ProductCommonDTO;
import com.hyundai.project.dto.ProductDetailDTO;

@SpringBootTest
public class AdminProductMapperTest {
	@Autowired
	private AdminProductMapper adminProductMapper;
	
	@Test
	public void getProductCommonTest() throws Exception {
		AdminProductDTO dto = new AdminProductDTO();
//		dto.setPname("셔츠");
		dto.setBname("LÄTT");
		
		List<AdminProductDTO> list= adminProductMapper.getProductCommon(dto);
		
		for(AdminProductDTO a : list) {
			System.out.println(a);
		}
	}
	
	@Test
	public void getBrandTest() throws Exception{
		List<BrandDTO> list = adminProductMapper.getBrand();
		for(BrandDTO a : list) {
			System.out.println(a);
		}
	}
	
	@Test
	public void getProductDetailTest() throws Exception {
		ProductCommonDTO dto = new ProductCommonDTO();
		dto.setPid("LC2B0KCD903W");
		
		List<ProductDetailDTO> list = adminProductMapper.getProductDetail(dto);
		
		for(ProductDetailDTO a : list) {
			System.out.println(a);
		}
	}
}
