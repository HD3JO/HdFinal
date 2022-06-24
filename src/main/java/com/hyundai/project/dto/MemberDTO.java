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

}
