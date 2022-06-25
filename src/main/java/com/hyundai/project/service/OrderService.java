package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.OrderDTO;
import com.hyundai.project.dto.OrderItemDTO;

public interface OrderService {
	
	public int insertOrder(OrderDTO orderDTO, List<OrderItemDTO> orderItemList) throws Exception;
	
	public List<OrderDTO> getOrderList(String email) throws Exception;
}
