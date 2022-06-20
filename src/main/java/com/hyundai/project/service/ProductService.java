package com.hyundai.project.service;



import java.util.List;

import com.hyundai.project.dto.ProductDetailDTO;

public interface ProductService {
	
	
	ProductDetailDTO getProductDetail(String pcid) throws Exception;
	
	
}
