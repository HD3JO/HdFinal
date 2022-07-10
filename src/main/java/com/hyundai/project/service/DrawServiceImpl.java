package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.DrawDTO;
import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.DrawRegDTO;
import com.hyundai.project.dto.DrawWinDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.product.repository.DrawMapper;

@Service
public class DrawServiceImpl implements DrawService {

	@Autowired
	DrawMapper drawMapper;  
	
	@Override
	public List<DrawListDTO> getDrawList() throws Exception {		
		
		return drawMapper.getDrawList();
	}

	@Override
	public DrawListDTO getDrawDetail(String psid) throws Exception {

		return drawMapper.getDrawDetail(psid);
	}

	@Override
	public int updateMileage(DrawDTO drawDTO) throws Exception {
				
		return drawMapper.updateMileage(drawDTO);
	}

	@Override
	public List<DrawWinDTO> getWinList() throws Exception {
		
		return drawMapper.getWinList();
	}

	@Override
	public int updateRegYn(List<DrawRegDTO> drawRegList) throws Exception {
		
		return drawMapper.updateRegYn(drawRegList);
	}

	@Override
	public List<DrawListDTO> getDrawListForAdmin() throws Exception {
		
		return drawMapper.getDrawListForAdmin();
	}

	@Override
	public MemberDTO getWinMember(String email) throws Exception {
		
		return drawMapper.getWinMember(email);
	}

	@Override
	public int updateOstaus(DrawDTO drawDTO) throws Exception {
		
		return drawMapper.updateOstaus(drawDTO);
	}

	@Override
	public int updateOstatusForAdmin(List<DrawWinDTO> modOstatusList) throws Exception {
		
		return drawMapper.updateOstatusForAdmin(modOstatusList);
	}
	
}
