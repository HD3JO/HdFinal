package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.BestProductDTO;
import com.hyundai.project.dto.NewProductDTO;

public interface MainProductService {

	public List<NewProductDTO> getNewProduct(String depth1name) throws Exception;
	
	public List<BestProductDTO> getBestProduct(String depth1name) throws Exception;
}
