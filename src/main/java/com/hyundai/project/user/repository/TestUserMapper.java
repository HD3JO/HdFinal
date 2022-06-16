package com.hyundai.project.user.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestUserMapper {
	
	public String getUser() throws Exception;
}
