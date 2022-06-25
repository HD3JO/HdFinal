package com.hyundai.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMemberDTO {
	 private String email;
	 private String password;
	 private String name;	 	 
	 
	 private String phone;
	 private String tel;
	 private String zipcode;
	 private String address1;
	 private String address2;
	 private String mrefid;
	 private String mlogintype;
	 private int enable;
	 private String role;
	 private int mileage;
}
