package com.hyundai.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class OrderPageDTO {
	private String psid;
	private String pname;
	private String bname;
	private String pccolorcode;
	private String pcimg1;
	private int pcprice;
	private String psize;
	private int pquantity;
}
