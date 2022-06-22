package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.BestProductDTO;
import com.hyundai.project.dto.NewProductDTO;
import com.hyundai.project.product.repository.MainProductMapper;

@Service
public class MainProductServiceImpl implements MainProductService {

	@Autowired
	private MainProductMapper mainProductMapper;
	
	@Override
	public List<NewProductDTO> getNewProduct(String depth1name) throws Exception {
		NewProductDTO newProductDTO = new NewProductDTO();
		//System.out.println(newProductMapper.getNewProduct("WOMEN"));
		return mainProductMapper.getNewProduct(depth1name);
	}

	@Override
	public List<BestProductDTO> getBestProduct(String depth1name) throws Exception {
		BestProductDTO bestProdctDTO = new BestProductDTO();
		
		return mainProductMapper.getBestProduct(depth1name);
	}

	
}
