package com.hyundai.project.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.NewProductDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductImgDTO;
import com.hyundai.project.dto.ProductListDTO;
import com.hyundai.project.dto.ProductSizeDTO;

@Service
public interface ProductService {
	
	List<ProductColorDTO> getProductColor(String pcid);
	
	ProductDetailDTO getProductDetail(String pcid) throws Exception;
	
	public List<ProductSizeDTO> getProductSize(String pcid);
	
	public List<NewProductDTO> getNewProduct(String category);	
	
	public List<ProductListDTO> getProductList(String depth1name, String depth2name, String depth3name, int pagenum, String pname);

	public ProductColorDTO getProductImgByPcid(String pcid);
	
	public int getProductCount(String depth1name, String depth2name, String depth3name, int pagenum, String pname);
}
