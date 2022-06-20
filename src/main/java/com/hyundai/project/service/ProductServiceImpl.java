package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.NewProductDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.product.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductMapper productmapper;
	
	@Override
	public List<ProductColorDTO> getProductColor(String pcid) {
		return productmapper.getProductColor(pcid);
		
	}

	@Override
	public List<ProductSizeDTO> getProductSize(String pcid) {
		return productmapper.getProductSize(pcid);
	}

	@Override
	public List<NewProductDTO> getNewProduct(String category) {
		// TODO Auto-generated method stub
		return productmapper.getNewProduct(category);
	}

	@Override
	public ProductDetailDTO getProductDetail(String pcid) throws Exception {
		// TODO Auto-generated method stub
		
		return productmapper.getProductDetail(pcid);
	}

}
