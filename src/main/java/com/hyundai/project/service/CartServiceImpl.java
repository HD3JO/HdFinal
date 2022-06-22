package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.user.repository.CartMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartMapper cartMapper;

	@Override
	public List<CartDTO> getCartList(String email) throws Exception {
		log.info("getCartList Service..................");
		
		CartDTO cartDTO = new CartDTO();
		cartDTO.setEmail(email);
		
		return cartMapper.getCartList(cartDTO.getEmail());
	}

	@Override
	public void cartInsert(CartDTO cartDTO) throws Exception {
		log.info("cartInsert Service..................");

		cartMapper.cartInsert(cartDTO);
	}

	@Override
	public int cartUpdate(CartDTO cartDTO) throws Exception {
		log.info("cartUpdate Service..................");
		
		return cartMapper.cartUpdate(cartDTO);
	}

	@Override
	public int cartDelete(CartDTO cartDTO) throws Exception {
		log.info("cartDelete Service..................");
		
		return cartMapper.cartDelete(cartDTO);
	}

	@Override
	public int selectDelete(List<CartDTO> cartList) throws Exception {
		log.info("selectDelete Service..................");
		
		return cartMapper.selectDelete(cartList);
	}

}
