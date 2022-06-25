package com.hyundai.project.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.OrderPageDTO;
import com.hyundai.project.dto.PaymentMethodDTO;

@Mapper
public interface OrderPageMapper {
	
	public List<OrderPageDTO> selectProduct(List<CartDTO> psidList);
	
	public List<PaymentMethodDTO> getPaymentMethod();
}
