package com.hyundai.project.product.repository;

import java.util.List;

import com.hyundai.project.dto.DrawDTO;
import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.DrawWinDTO;
import com.hyundai.project.dto.MemberDTO;

public interface DrawMapper {
	
	public List<DrawListDTO> getDrawList() throws Exception;
	
	public DrawListDTO getDrawDetail(String psid) throws Exception;
	
	public int updateMileage(DrawDTO drawDTO) throws Exception;
	
	public MemberDTO getPhoneByEmail(String email) throws Exception;
	
	public int insertWinDraw(DrawWinDTO drawWinDTO) throws Exception;
}
