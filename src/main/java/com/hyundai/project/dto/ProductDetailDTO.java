package com.hyundai.project.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
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
}
