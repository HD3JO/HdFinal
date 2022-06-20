package com.hyundai.project.user.repository;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRole;

@SpringBootTest
public class MemberMapperTests {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void insertMemberTest() throws SQLException {
		MemberDTO member = new MemberDTO();
		member.setEmail("seokjune96@gmail.com");
		member.setPassword(passwordEncoder.encode("1234"));
		member.setName("고석준");
		member.setAddress("서울시 강남구 테헤란로 250");
		member.setPhone("010-1111-2222");
		member.setChannel("c");
		member.setEnable(1);
		member.setRole(MemberRole.USER);
		
		memberMapper.insertMember(member);
	}
	
	@Test
	public void selectMemberTest() throws Exception {
		MemberDTO member = new MemberDTO();
		member.setEmail("seokjune96@gmail.com");
		//member.setPassword(passwordEncoder.encode("1234"));
		member.setName("고석준2");
		member.setAddress("서울시 강남구 테헤란로 250");
		member.setPhone("010-1111-2222");
		member.setChannel("c");
		member.setEnable(1);
		member.setRole(MemberRole.USER);
		
		System.out.println(memberMapper.getUser(member).get(0));
	}
	
	@Test
	public void testMemberUpdate() throws Exception{
		MemberDTO searchMember = new MemberDTO();
		searchMember.setChannel("c");
		searchMember=memberMapper.getUser(searchMember).get(0);
		
		searchMember.setName("고석준2");
		memberMapper.updateMember(searchMember);
	}
	
	@Test
	public void testMemberDelete() throws Exception{
		memberMapper.deleteMember("QQQ@NAVER.COM");
	}
}