package com.hyundai.project.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberUserDetails;
import com.hyundai.project.user.repository.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MemberUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberDTO vo = new MemberDTO();
		vo.setEmail(username);
		List<MemberDTO> result=null;
		try {
			result = memberMapper.getUser(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MemberDTO  result2 = result.get(0);
		if(result2.getEnable() == 0) {	//휴면계정 
			return null;
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + result2.getRole()));
		
		
		//MemberDTO -> MemberUserDetails 변화
		MemberUserDetails memberUserDetails = new MemberUserDetails(result2.getEmail(), result2.getPassword()
				, 0, authorities, result2.getGrade());
		
		//MemberUserDetails 값 세팅
		memberUserDetails.setEmail(result2.getEmail());
		memberUserDetails.setName(result2.getName());
		System.out.println(memberUserDetails.toString());
		return memberUserDetails;
	}

}
