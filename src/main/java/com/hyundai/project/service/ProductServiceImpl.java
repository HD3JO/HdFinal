package com.hyundai.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.BestProductDTO;
import com.hyundai.project.dto.CategoryDTO;
import com.hyundai.project.dto.NewProductDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductImgDTO;
import com.hyundai.project.dto.ProductListDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.dto.ProductViewDTO;
import com.hyundai.project.dto.PurchasedProductDTO;
import com.hyundai.project.product.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	ProductMapper productmapper;
	
	@Override
	public List<BestProductDTO> getBestProduct(String category) {
		// TODO Auto-generated method stub
		return productmapper.getBestProduct(category);
	}

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

	//상품들 기본 정보
	//상품 이미지들
	//상품 사이즈들
	//상품 컬러코드
	@Override
	public List<ProductListDTO> getProductList(String depth1name, String depth2name, String depth3name, int pagenum, String pname) {
		// TODO Auto-generated method stub
		List<ProductListDTO> result = new ArrayList<>();
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDepth1name(depth1name);
		categoryDTO.setDepth2name(depth2name);
		categoryDTO.setDepth3name(depth3name);
		categoryDTO.setPagenum(pagenum);
		categoryDTO.setPname(pname);
		List<ProductViewDTO> list1 =  productmapper.getProductListByCategory(categoryDTO);
		//list1을 ProductListDTO에 매칭시켜야함 
		for(ProductViewDTO a : list1) {
			ProductListDTO dto = new ProductListDTO();
			dto.setPid(a.getPid());
			dto.setPname(a.getPname());
			dto.setPcprice(a.getPcprice());
			dto.setBname(a.getBname());
			dto.setCateNo(a.getCateNo());
			
			//상품이미들 
			List<ProductImgDTO> list2 = productmapper.getProductImgByPid(a.getPid());
			dto.setProductImgList(list2);
			//상품사이즈들
			List<ProductSizeDTO> list3 = productmapper.getProductsizeByPid(a.getPid());
			dto.setProductSizeList(list3);
			//상품컬러코드
			List<ProductColorDTO> list4 = productmapper.getProductColorCodeByPid(a.getPid());
			dto.setProductColorList(list4);
			dto.setPcid(list4.get(0).getPcid());
			
			
			result.add(dto);
		}
		
		
		return result;
	}

	@Override
	public ProductColorDTO getProductImgByPcid(String pcid) {
		// TODO Auto-generated method stub
		return productmapper.getProductImgByPcid(pcid);
		
	}

	@Override
	public int getProductCount(String depth1name, String depth2name, String depth3name, int pagenum, String pname) {
		// TODO Auto-generated method stub
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDepth1name(depth1name);
		categoryDTO.setDepth2name(depth2name);
		categoryDTO.setDepth3name(depth3name);
		categoryDTO.setPagenum(pagenum);
		categoryDTO.setPname(pname);
		
		return productmapper.getProductCount(categoryDTO);
	}
	
	@Override
	public List<CategoryDTO> getCategoryList(){
		return productmapper.getCategoryList();
	}
	

}
