package com.hyundai.project.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.NewProductDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.service.ProductService;

@RestController
public class ProductRESTController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value="/ProductDetail")
	public ProductDetailDTO getProductDetail(@RequestBody ProductDetailDTO detailDTO) throws Exception {
		// 상품 상세 정보 조회
		// http://localhost:8080/ProductDetail?pcid=CM2B0KTO406WPK
		return productService.getProductDetail(detailDTO.getPcid());
	}
	
	@PostMapping(value="/ProductColor")
	public List<ProductColorDTO> getProductColor(@RequestBody ProductColorDTO colorDTO) {
		//해당 제품의 모든 컬러 조회
		// http://localhost:8080/ProductColor?pcid=CM2B0KTO406WPK
		return productService.getProductColor(colorDTO.getPcid());
	}
	
	@PostMapping(value="/ProductSize")
	public List<ProductSizeDTO> getProductSize(@RequestBody ProductSizeDTO sizeDTO) {
		// 해당 제품의 모든 사이즈 조회
		// http://localhost:8080/ProductSize?pcid=CM2B0KTO406WPK
		return productService.getProductSize(sizeDTO.getPcid());
	}
	
	@PostMapping(value="/NewProduct")
	public List<NewProductDTO> getNewProduct(@RequestBody NewProductDTO newProductDTO) {
		// 카테고리별(MEN/WOMEN/LIFESTYLE/KIDS) 신상품 20개 이하로 조회
		// http://localhost:8080/NewProduct?category=MEN
		return productService.getNewProduct(newProductDTO.getCategory());
	}
	
}
