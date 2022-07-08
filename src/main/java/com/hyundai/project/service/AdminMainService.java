package com.hyundai.project.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminMainService {
	
	public int getTotalProductQty();
	
	public int getTotalUser();
	
	public int getMonthOrderPrice();
	
	public int getMonthOrderCount();
}
