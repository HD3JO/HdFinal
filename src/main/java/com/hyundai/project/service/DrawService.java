package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.DrawDTO;
import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.DrawRegDTO;
import com.hyundai.project.dto.DrawWinDTO;
import com.hyundai.project.dto.MemberDTO;

public interface DrawService {
	
	public List<DrawListDTO> getDrawList() throws Exception;
	
	public DrawListDTO getDrawDetail(String psid) throws Exception;
	
	public int updateMileage(DrawDTO drawDTO) throws Exception;
	
	public List<DrawWinDTO> getWinList() throws Exception;
	
	public int updateRegYn(List<DrawRegDTO> drawRegList) throws Exception;
	
	public List<DrawListDTO> getDrawListForAdmin() throws Exception;
	
	public MemberDTO getWinMember(String email) throws Exception;
	
	public int updateOstaus(DrawDTO drawDTO) throws Exception;
	
	public int updateOstatusForAdmin(List<DrawWinDTO> modOstatusList) throws Exception;
}
