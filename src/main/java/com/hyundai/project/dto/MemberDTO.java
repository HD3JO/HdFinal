package com.hyundai.project.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO implements Serializable{

	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String channel;
	private int enable;
	private String role;
	private Date regdate;
	private Date moddate;
}
