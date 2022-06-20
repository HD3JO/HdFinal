package com.hyundai.project.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRole;
import com.hyundai.project.dto.MemberUserDetails;
import com.hyundai.project.user.repository.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberOAuth2UserDetailsService
       extends DefaultOAuth2UserService{
	
	@Autowired
	private MemberMapper membermapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private MemberDTO saveSocialMember(String email) throws Exception {
		System.out.println("Email  is : " +email);
		MemberDTO result = null;
		MemberDTO vo = new MemberDTO();
		vo.setEmail(email);
		List<MemberDTO> list = membermapper.getUser(vo);
		System.out.println("검색된 유저의 수 " + list.size());
		if(list.size() > 0) {
			result = list.get(0);
			return result;
		}else {
			MemberDTO member = new MemberDTO();
			member.setEmail(email);
			member.setChannel("G");
			member.setEnable(1);
			member.setName(email);
			member.setPassword(passwordEncoder.encode("1111"));
			member.setRole(MemberRole.USER);
			membermapper.insertMember(member);
			
			result = membermapper.getUser(member).get(0);
			return result;
		}
	} 
	
   @Override
   public OAuth2User loadUser(OAuth2UserRequest userRequest)
           throws OAuth2AuthenticationException {
       
       String clientName = userRequest.getClientRegistration()
               .getClientName();
      
       //사용자 정보 가져오기 구글에서 허용한 API 범위
       OAuth2User oAuth2User = super.loadUser(userRequest);
       oAuth2User.getAttributes().forEach( ( k , v ) ->{
    	   System.out.println(k + " : " + v);
       });//end foreach
       
       
       String email = null;
       
       if(clientName.equals("Google")) {
    	   email = oAuth2User.getAttribute("email");
    	   
       }
       
       MemberDTO memberVO;
       try {
    	   memberVO = saveSocialMember(email);
    	   List<GrantedAuthority> authorities = new ArrayList<>();
	       authorities.add(
	               new SimpleGrantedAuthority("ROLE_" + memberVO.getRole()));      
	       
	       MemberUserDetails memberUserDetails = new MemberUserDetails(memberVO.getEmail(), memberVO.getPassword(), 1, authorities,oAuth2User.getAttributes());
	       memberUserDetails.setName(memberVO.getName());
	       memberUserDetails.setChannel(memberVO.getChannel());
	       return memberUserDetails;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
       
   }  
}