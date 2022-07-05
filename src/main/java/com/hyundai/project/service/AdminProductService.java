package com.hyundai.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AdminProductDTO;
import com.hyundai.project.dto.BrandDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductStockDTO;

@Service
public interface AdminProductService {
	public List<AdminProductDTO> getProductCommon(AdminProductDTO adminProductCommon) throws Exception;
	
	public List<BrandDTO> getBrand() throws Exception;
	
	public List<ProductDetailDTO> getProductDetail(String pid) throws Exception;

	public void updateProductStock(List<ProductStockDTO> productStockDTOList) throws Exception;
}
