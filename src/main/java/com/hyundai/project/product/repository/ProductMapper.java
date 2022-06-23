package com.hyundai.project.product.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.CategoryDTO;
import com.hyundai.project.dto.NewProductDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductImgDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.dto.ProductViewDTO;

@Mapper
public interface ProductMapper {
	public String getProduct() throws Exception;
	
	public List<ProductColorDTO> getProductColor(String pcid);
	
	public ProductDetailDTO getProductDetail(String pcid);
	
	public List<ProductSizeDTO> getProductSize(String pcid);
	
	public List<NewProductDTO> getNewProduct(String category);
	
	public List<ProductViewDTO> getProductListByCategory(CategoryDTO categoryDTO);
	
	public List<ProductImgDTO> getProductImgByPid(String pid);
	
	public List<ProductSizeDTO> getProductsizeByPid(String pid);
	
	public List<ProductColorDTO> getProductColorCodeByPid(String pid);
	
	public ProductColorDTO getProductImgByPcid(String pcid);
	
	public int getProductCount(CategoryDTO categoryDTO);
	
	public List<CategoryDTO> getCategoryList();
	
}
