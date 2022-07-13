package com.hyundai.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MemberDTO;

@Service
public interface AdminMainService {
	
	public int getTotalProductQty();
	
	public int getTotalUser();
	
	public int getMonthOrderPrice();
	
	public int getMonthOrderCount();
	
	public int getMemberGrade(String grade) throws Exception;
	
	public List<MemberDTO> rankMember() throws Exception;
}
