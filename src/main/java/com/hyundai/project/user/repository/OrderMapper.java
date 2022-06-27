package com.hyundai.project.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.OrderDTO;
import com.hyundai.project.dto.OrderItemDTO;

@Mapper
public interface OrderMapper {
	
	public int insertOrder(OrderDTO orderDTO) throws Exception;
	
	public int insertOrderItem(List<OrderItemDTO> orderItemList) throws Exception;
	
	public List<OrderDTO> getOrderList(String email) throws Exception;
	
	public List<OrderItemDTO> getOrderListForAdmin() throws Exception;
	
	public int updateOrder(OrderItemDTO orderItemDTO) throws Exception;
}
