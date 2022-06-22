package com.hyundai.project.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductListDTO {
	private String bname;
	private String pid;
	private String pname;
	private int cateNo;
	private String pcimg1;
	private String pcid;
	private String pcchipimg;
	private String pcprice;
	
	//상품이미지들
	private List<ProductImgDTO> productImgList;
	//상품 사이즈들
	private List<ProductSizeDTO> productSizeList;
	//상품 컬러코드들 
	private List<ProductColorDTO> productColorList;
}
