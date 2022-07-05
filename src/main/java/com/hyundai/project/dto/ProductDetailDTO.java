package com.hyundai.project.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailDTO {
	
	private String bname;
	private String pname;
	private int pcprice;
	private String pcid;
	private String pnote;
	private String psize;
	private String pcchipimg;
	private String pccolorcode;
	private String pcimg1;
	private String pcimg2;
	private String pcimg3;
	
	private int psstock;
	private String psid;
}
