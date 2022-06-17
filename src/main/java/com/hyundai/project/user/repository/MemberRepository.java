package com.hyundai.project.user.repository;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.MemberDTO;

@Mapper
public interface MemberRepository {
	public MemberDTO selectMember(MemberDTO memberDTO);

}
