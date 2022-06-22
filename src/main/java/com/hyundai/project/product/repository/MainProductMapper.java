package com.hyundai.project.product.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.BestProductDTO;
import com.hyundai.project.dto.NewProductDTO;

@Mapper
public interface MainProductMapper {

	public List<NewProductDTO> getNewProduct(String depth1name) throws Exception;
	
	public List<BestProductDTO> getBestProduct(String depth1name) throws Exception;
}
