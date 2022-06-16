package com.hyundai.project.product.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestProductMapper {
	public String getProduct() throws Exception;
}
