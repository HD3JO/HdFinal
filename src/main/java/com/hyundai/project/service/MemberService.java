package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.MemberDTO;

public interface MemberService {

	public List<MemberDTO> selectAllUser() throws Exception;
	
	int insertMember(MemberDTO memberDTO) throws Exception;
	
	public MemberDTO selectOneUser(String email) throws Exception;
	
	public MemberDTO updateMember(MemberDTO memberDTO) throws Exception;
	
}
