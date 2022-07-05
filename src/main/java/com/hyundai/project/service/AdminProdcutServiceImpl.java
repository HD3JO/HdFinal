package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AdminProductDTO;
import com.hyundai.project.dto.BrandDTO;
import com.hyundai.project.dto.ProductCommonDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductStockDTO;
import com.hyundai.project.product.repository.AdminProductMapper;

@Service
public class AdminProdcutServiceImpl implements AdminProductService {

	@Autowired
	private AdminProductMapper adminProductMapper;
	
	@Override
	public List<AdminProductDTO> getProductCommon(AdminProductDTO adminProductCommon) throws Exception {
		// TODO Auto-generated method stub
		return adminProductMapper.getProductCommon(adminProductCommon);
	}

	@Override
	public List<BrandDTO> getBrand() throws Exception {
		// TODO Auto-generated method stub
		return adminProductMapper.getBrand();
	}

	@Override
	public List<ProductDetailDTO> getProductDetail(String pid) throws Exception {
		ProductCommonDTO dto = new ProductCommonDTO();
		dto.setPid(pid);
		return adminProductMapper.getProductDetail(dto);
		
	}

	@Override
	public void updateProductStock(List<ProductStockDTO> productStockDTOList) throws Exception {
		// TODO Auto-generated method stub
		for(ProductStockDTO dto : productStockDTOList) {
			adminProductMapper.updateProductStock(dto);
		}
		
		
	}

}
