package com.hyundai.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.product.repository.TestProductMapper;
import com.hyundai.project.user.repository.TestUserMapper;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	TestProductMapper mapper1;	
	
	@Autowired
	TestUserMapper mapper2;
	
	@Override
	public String user() throws Exception{
		System.out.println("Product Service start.....................");
		
		return mapper1.getProduct();
	}

	@Override
	public String product() throws Exception {
		System.out.println("User Service start.....................");
		
		return mapper2.getUser();
	}

}
