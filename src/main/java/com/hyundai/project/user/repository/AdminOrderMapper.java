package com.hyundai.project.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.AdminOrderDTO;
import com.hyundai.project.dto.OrderDTO;

@Mapper
public interface AdminOrderMapper {
	
	public List<AdminOrderDTO> getOrderList(AdminOrderDTO adminOrderDTO) throws Exception;

	public void updateOrder(AdminOrderDTO adminOrderDTO);
}

