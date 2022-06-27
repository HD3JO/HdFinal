package com.hyundai.project.dto;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberUserDetails extends User implements OAuth2User{
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private String channel;
	private String grade;
	
	private Map<String, Object> OA2_attr;
	
	public MemberUserDetails(String username, String password, int fromSocial
			, List<GrantedAuthority> authorities, String grade) {
		super(username, password, authorities);
		this.email = username;
		this.grade = grade;
	}
	
	public MemberUserDetails(String username, String password, int fromsocial,
			List<GrantedAuthority> authorities, Map<String, Object> OA2_attr, String grade) {
		this(username, password, fromsocial, authorities, grade);
		this.OA2_attr = OA2_attr;
		this.email = username;
		this.grade = grade;
		
	}
	
	public Map<String, Object> getAttributes() {
		return OA2_attr;
	}

}
