package com.hyundai.project.user.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	public List<MemberDTO> getUser(MemberDTO memberDTO) throws Exception;
	
	public int insertMember(MemberDTO memberDTO) throws SQLException;
	
	public MemberDTO updateMember(MemberDTO memberDTO) throws SQLException;
	
	public void deleteMember(String email) throws SQLException;
	
	public List<MemberDTO> selectUserAdmin(MemberDTO memberDTO) throws Exception;

	public void secession(MemberDTO dto);
	
	
	//public void updateMemberAdmin(MemberDTO memberDTO) throws SQLException;
}