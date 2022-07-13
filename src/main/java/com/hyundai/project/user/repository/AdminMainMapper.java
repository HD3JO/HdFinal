package com.hyundai.project.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.MemberDTO;

@Mapper
public interface AdminMainMapper {

	public int getTotalProductQty();
	
	public int getTotalUser();
	
	public int getMonthOrderPrice();
	
	public int getMonthOrderCount();
	
	public int getMemberGrade(String grade) throws Exception;
	
	public List<MemberDTO> rankMember() throws Exception;
}
