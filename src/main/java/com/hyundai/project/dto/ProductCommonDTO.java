package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommonDTO {

	private String pid;
	private String pname;
	private String pnote;
	private int bno;
	private int pstatus;
	
	private BrandDTO brandDTO;
	private CategoryDTO categoryDTO;
	private ProductStockDTO productStockDTO;
	private ProductCategoryDTO productCategoryDTO;
	private ProductColorDTO productColorDTO;
	private ProductImageDTO productImageDTO;
}
