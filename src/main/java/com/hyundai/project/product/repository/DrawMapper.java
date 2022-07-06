package com.hyundai.project.product.repository;

import java.util.List;

import com.hyundai.project.dto.DrawDTO;
import com.hyundai.project.dto.DrawListDTO;

public interface DrawMapper {
	
	public List<DrawListDTO> getDrawList() throws Exception;
	
	public DrawListDTO getDrawDetail(String psid) throws Exception;
	
	public int updateMileage(DrawDTO drawDTO) throws Exception;
}
