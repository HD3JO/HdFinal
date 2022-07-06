package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.DrawDTO;
import com.hyundai.project.dto.DrawListDTO;

public interface DrawService {
	
	public List<DrawListDTO> getDrawList() throws Exception;
	
	public DrawListDTO getDrawDetail(String psid) throws Exception;
	
	public int updateMileage(DrawDTO drawDTO) throws Exception;
}
