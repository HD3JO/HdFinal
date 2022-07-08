package com.hyundai.project.user.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMainMapper {

	public int getTotalProductQty();
	
	public int getTotalUser();
	
	public int getMonthOrderPrice();
	
	public int getMonthOrderCount();
}
