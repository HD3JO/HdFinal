package com.hyundai.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.OrderCompleteDTO;
import com.hyundai.project.dto.OrderDTO;
import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.user.repository.CartMapper;
import com.hyundai.project.user.repository.OrderMapper;
import com.hyundai.project.dto.OrderListDTO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	CartMapper cartMapper;

	@Transactional
	@Override
	public int insertOrder(OrderDTO orderDTO, List<OrderItemDTO> orderItemList) throws Exception {
		
		int rows = orderMapper.insertOrder(orderDTO);
		int oid = orderDTO.getOid();
		
		for(OrderItemDTO dto : orderItemList) {
			dto.setOid(oid);
		}
		
		rows += orderMapper.insertOrderItem(orderItemList);
		
		List<CartDTO> cartList = new ArrayList<>();
		for(OrderItemDTO dto : orderItemList) {
			CartDTO cartDTO = new CartDTO();
			cartDTO.setPsid(dto.getPsid());
			cartDTO.setEmail(orderDTO.getEmail());
			cartList.add(cartDTO);
			
		}
		
		orderMapper.updateMileage(orderDTO);
		orderMapper.updateStock(orderItemList);
		
		if(rows > 0) {
			cartMapper.selectDelete(cartList);
		}
		
		return rows;
	}

	@Override
	public List<OrderDTO> getOrderList(String email) throws Exception {
		
		return orderMapper.getOrderList(email);
	}

	@Override
	public List<OrderItemDTO> getOrderListForAdmin() throws Exception {
		
		return orderMapper.getOrderListForAdmin();
	}

	@Override
	public int updateOrder(OrderItemDTO orderItemDTO) throws Exception {
		
		return orderMapper.updateOrder(orderItemDTO);
	}
	
	@Override
	public List<OrderListDTO> selectOrderList(String email) throws Exception {
		OrderListDTO orderListDTO = new OrderListDTO();
		return orderMapper.selectOrderList(email);
	}
	
	@Override
	public List<OrderListDTO> selectOrderListByOneMonth(String email) throws Exception {
		OrderListDTO orderListDTO = new OrderListDTO();
		return orderMapper.selectOrderListByOneMonth(email);
	}

	@Override
	public List<OrderCompleteDTO> getOrderComplete(String oid) throws Exception {
		
		return orderMapper.getOrderComplete(oid);
	}
}

