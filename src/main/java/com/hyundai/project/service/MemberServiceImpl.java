package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.user.repository.MemberMapper;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper mapper2;
	
	@Override
	public List<MemberDTO> selectAllUser() throws Exception{
		System.out.println("Product Service start.....................");
		MemberDTO memberDTO = new MemberDTO();
		
		return mapper2.getUser(memberDTO);
	}
	@Override
	public int insertMember(MemberDTO memberDTO) throws Exception{
		 return mapper2.insertMember(memberDTO);
	}
	@Override
	public MemberDTO selectOneUser(String email) throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setEmail(email);
		List<MemberDTO> memberList = mapper2.getUser(memberDTO);
		if(memberList.size() == 0) {
			return null;
		}else {
			return memberList.get(0);
		}
	}

}
