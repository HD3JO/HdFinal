package com.hyundai.project.user.repository;

import com.hyundai.project.dto.OrderMemberDTO;

public interface OrderMemberMapper {
	
	public OrderMemberDTO getOrderMember(String email);
}
