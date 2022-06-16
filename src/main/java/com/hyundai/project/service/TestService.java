package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.MemberDTO;

public interface TestService {

	public List<MemberDTO> user() throws Exception;
	
	public String product() throws Exception;
}
