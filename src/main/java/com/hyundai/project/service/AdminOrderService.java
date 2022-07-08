package com.hyundai.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AdminOrderDTO;
import com.hyundai.project.dto.OrderDTO;

@Service
public interface AdminOrderService {
	public List<AdminOrderDTO> getOrderList(AdminOrderDTO adminOrderDTO) throws Exception;

	public void updateOrder(List<AdminOrderDTO> adminOrderDTOList);
}
