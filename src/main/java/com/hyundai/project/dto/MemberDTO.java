package com.hyundai.project.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO implements Serializable{
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String channel;
	private int enable;
	private MemberRole role;
	private Date regdate;
	private Date moddate;

	private String marketingemail;
	private String marketingsms;
	
	private String grade;
	private int mileage;
	
	public void updateMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		if(dto.getEmail() != null) {
			this.email = dto.getEmail();
		}
		if(dto.getName() != null) {
			this.name = dto.getName();
		}
		if(dto.getAddress() != null) {
			this.address = dto.getAddress();
		}
		if(dto.getPhone() != null) {
			this.phone = dto.getPhone();
		}
		if(dto.getChannel() != null) {
			this.channel = dto.getChannel();
		}
		if(dto.getEnable() == 0) {
			this.enable = 0;
		}
		if(dto.getEnable()==1) {
			this.enable = 1;
		}
			
		if(dto.getRole() !=null) {
			this.role = dto.getRole();
		}
		if(dto.getRegdate() != null) {
			this.regdate = dto.getRegdate();
		}
		if(dto.getModdate() != null) {
			this.moddate = dto.getModdate();
		}
		if(dto.getMarketingemail() != null) {
			this.marketingemail = dto.getMarketingemail();
		}
		if(dto.getMarketingsms() != null) {
			this.marketingsms = dto.getMarketingsms();
		}
		if(dto.getGrade() != null) {
			this.grade = dto.getGrade();
		}
		
		this.mileage = dto.getMileage();
	}

}
