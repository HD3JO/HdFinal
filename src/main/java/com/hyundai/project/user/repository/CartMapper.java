package com.hyundai.project.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.CartDTO;

@Mapper
public interface CartMapper {
	
	public List<CartDTO> getCartList(String email) throws Exception;
	
	public int cartInsert(CartDTO cartDTO) throws Exception;
	
	public int cartUpdate(CartDTO cartDTO) throws Exception;
	
	public int cartDelete(CartDTO cartList) throws Exception;
}
