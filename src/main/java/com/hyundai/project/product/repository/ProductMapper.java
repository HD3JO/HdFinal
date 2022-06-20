package com.hyundai.project.product.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.NewProductDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.productSizeDTO;

@Mapper
public interface ProductMapper {
	public String getProduct() throws Exception;
	
	public List<ProductColorDTO> getProductColor(String pcid);
	
	public ProductDetailDTO getProductDetail(String pcid);
	
	public List<productSizeDTO> getProductSize(String pcid);
	
	public List<NewProductDTO> getNewProduct(String category);
	
}
