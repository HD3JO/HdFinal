package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.OrderPageDTO;
import com.hyundai.project.dto.PaymentMethodDTO;

public interface OrderPageService {
	
	public List<OrderPageDTO> selectProduct(List<CartDTO> psidList);
	
	public List<PaymentMethodDTO> getPaymentMethod();
}
