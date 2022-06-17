package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.product.repository.TestProductMapper;
import com.hyundai.project.user.repository.MemberMapper;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper mapper2;
	
	@Override
	public List<MemberDTO> selectUser() throws Exception{
		System.out.println("Product Service start.....................");
		MemberDTO memberDTO = new MemberDTO();
		
		return mapper2.getUser(memberDTO);
	}

}
