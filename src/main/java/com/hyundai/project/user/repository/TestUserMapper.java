package com.hyundai.project.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.MemberDTO;

@Mapper
public interface TestUserMapper {
	
	public List<MemberDTO> getUser(MemberDTO memberDTO) throws Exception;
}
