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
	@Override
	public MemberDTO updateMember(MemberDTO memberDTO) throws Exception{
		System.out.println(memberDTO.getEmail());
		MemberDTO vo = new MemberDTO();
		vo.setEmail(memberDTO.getEmail());
		MemberDTO updateUser =mapper2.getUser(vo).get(0);
		
		updateUser.setName(memberDTO.getName());
		updateUser.setPhone(memberDTO.getPhone());
		updateUser.setPassword(memberDTO.getPassword());
		updateUser.setMarketingemail(memberDTO.getMarketingemail());
		updateUser.setMarketingsms(memberDTO.getMarketingsms());
		
		return mapper2.updateMember(updateUser);
	}
	
	@Override
	public List<MemberDTO> selectUserAdmin(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		return mapper2.selectUserAdmin(memberDTO);
	}
	@Override
	public void updateMemeberAdmin(List<MemberDTO> memberList) throws Exception {
		// TODO Auto-generated method stub
		for(MemberDTO dto : memberList) {
			String email = dto.getEmail();
			
			MemberDTO updateMember = new MemberDTO();
			updateMember.setEmail(email);
			updateMember = mapper2.getUser(updateMember).get(0);
			
			updateMember.updateMember(dto);
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(updateMember);
			mapper2.updateMember(updateMember);
		}
		
	}
	
	@Override
	public void secession(String email) {
		MemberDTO dto = new MemberDTO();
		dto.setEmail(email);
		mapper2.secession(dto);
		
	}

}
