package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.user.repository.AdminMainMapper;

@Service
public class AdminMainServiceImpl implements AdminMainService {

	@Autowired
	private AdminMainMapper adminMapper;
	
	
	@Override
	public int getTotalProductQty() {
		// TODO Auto-generated method stub
		return adminMapper.getTotalProductQty();
	}

	@Override
	public int getTotalUser() {
		// TODO Auto-generated method stub
		return adminMapper.getTotalUser();
	}

	@Override
	public int getMonthOrderPrice() {
		// TODO Auto-generated method stub
		return adminMapper.getMonthOrderPrice();
	}

	@Override
	public int getMonthOrderCount() {
		// TODO Auto-generated method stub
		return adminMapper.getMonthOrderCount();
	}

	@Override
	public int getMemberGrade(String grade) throws Exception {
		
		return adminMapper.getMemberGrade(grade);
	}

	@Override
	public List<MemberDTO> rankMember() throws Exception {
		
		return adminMapper.rankMember();
	}
}
