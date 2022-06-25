package com.hyundai.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.OrderMemberDTO;
import com.hyundai.project.user.repository.OrderMemberMapper;

@Service
public class OrderMemberServiceImpl implements OrderMemberService {

	@Autowired
	private OrderMemberMapper orderMemberMapper;
	
	@Override
	public OrderMemberDTO getOrderMember(String email) {
		
		return orderMemberMapper.getOrderMember(email);
	}
}
