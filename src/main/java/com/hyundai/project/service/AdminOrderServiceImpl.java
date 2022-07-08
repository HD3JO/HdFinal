package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AdminOrderDTO;
import com.hyundai.project.user.repository.AdminOrderMapper;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
	@Autowired
	private AdminOrderMapper adminOrderMapper;

	@Override
	public List<AdminOrderDTO> getOrderList(AdminOrderDTO adminOrderDTO) throws Exception {
		// TODO Auto-generated method stub
		return adminOrderMapper.getOrderList(adminOrderDTO);
	}

	@Override
	public void updateOrder(List<AdminOrderDTO> adminOrderDTOList) {
		// TODO Auto-generated method stub
		for(AdminOrderDTO a : adminOrderDTOList) {
			adminOrderMapper.updateOrder(a);
		}
		
	}

}
