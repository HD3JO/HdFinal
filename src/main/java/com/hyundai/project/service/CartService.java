package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.CartDTO;

public interface CartService {
	public List<CartDTO> getCartList(String email) throws Exception;
	
	public void cartInsert(CartDTO cartDTO) throws Exception;
	
	public int cartUpdate(CartDTO cartDTO) throws Exception;
	
	public int cartDelete(CartDTO cartList) throws Exception;
	
	public int selectDelete(List<CartDTO> cartList) throws Exception;

}
