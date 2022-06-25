package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.OrderPageDTO;
import com.hyundai.project.dto.PaymentMethodDTO;
import com.hyundai.project.user.repository.OrderPageMapper;

@Service
public class OrderPageServiceImpl implements OrderPageService {

	@Autowired
	private OrderPageMapper orderPageMapper;
	
	@Override
	public List<OrderPageDTO> selectProduct(List<CartDTO> psidList) {
		
		return orderPageMapper.selectProduct(psidList);
	}

	@Override
	public List<PaymentMethodDTO> getPaymentMethod() {
		
		return orderPageMapper.getPaymentMethod();
	}
}
