package com.hyundai.project.product.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.AdminProductDTO;
import com.hyundai.project.dto.BrandDTO;
import com.hyundai.project.dto.ProductCommonDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductStockDTO;

@Mapper
public interface AdminProductMapper {
	public List<AdminProductDTO> getProductCommon(AdminProductDTO adminProductCommon) throws Exception;
	
	public List<BrandDTO> getBrand() throws Exception;
	
	public List<ProductDetailDTO> getProductDetail(ProductCommonDTO productCommonDTO) throws Exception;

	public void updateProductStock(ProductStockDTO dto);
}
