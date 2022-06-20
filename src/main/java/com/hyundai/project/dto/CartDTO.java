package com.hyundai.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class CartDTO {
	
	private String email; 
	private String psid;   
	private int pquantity;
	
	private String pcImg1;
	private String bname;
	private String pname;
	private String pcColorCode;
	private String psize;
	private int pcprice;
	

}
