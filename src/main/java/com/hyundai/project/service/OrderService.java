package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.OrderCompleteDTO;
import com.hyundai.project.dto.OrderDTO;
import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;

public interface OrderService {
	
	public int insertOrder(OrderDTO orderDTO, List<OrderItemDTO> orderItemList) throws Exception;
	
	public List<OrderDTO> getOrderList(String email) throws Exception;
	
	public List<OrderItemDTO> getOrderListForAdmin() throws Exception;
	
	public int updateOrder(OrderItemDTO orderItemDTO) throws Exception;

	public List<OrderListDTO> selectOrderList(String email) throws Exception;
	
	public List<OrderListDTO> selectOrderListByOneMonth(String email) throws Exception;
	
	public List<OrderCompleteDTO> getOrderComplete(String oid) throws Exception;
}
