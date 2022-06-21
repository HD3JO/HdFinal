package com.hyundai.project.product.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.ProductAddSizeDTO;
import com.hyundai.project.dto.ProductViewDTO;

@Mapper
public interface ProductListMapper {
	
	public List<ProductViewDTO> productList(int cateNo);
	
	public List<ProductAddSizeDTO> productAddSize(String pid);

//	public List<ProductCommonDTO> selectProductList1(@Param("depthname1") String depthname1);
//	
//	public List<ProductCommonDTO> selectProductList2(@Param("depthname2") String depthname2);
//	
//	public List<ProductCommonDTO> selectProductList3(@Param("depthname3") String depthname3);
//	
//	public List<ProductCommonDTO> selectProductList4(@Param("cateNo") int cateNo);
}
