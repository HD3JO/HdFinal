package com.hyundai.project.dto;

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
public class ProductViewDTO {

	private String bname;
	private String pid;
	private String pname;
	private int cateNo;
	private String pcimg1;
	private String pcid;
	private String pcchipimg;
	private String pcprice;
}
