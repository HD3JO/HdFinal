package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.MemberDTO;

public interface MemberService {

	public List<MemberDTO> selectUser() throws Exception;
	
}
